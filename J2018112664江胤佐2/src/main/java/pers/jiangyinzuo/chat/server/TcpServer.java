package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Jiang Yinzuo
 */
public class TcpServer implements ClientHandler.ClientHandlerCallback {
    /**
     * 服务器端口号
     */
    private final int port;

    /**
     * 监听客户端连接
     */
    private ClientListener clientListener;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ForwardingMessageManager forwardingMessageManager = ForwardingMessageManager.newForwardingMessageManager(this);

    private ExecutorService clientListenerThreadPool;

    private boolean serverIsOn = false;
    /**
     * 已连接上的客户端处理器映射
     */
    static Map<Long, ClientHandler> clientHandlerMap = new HashMap<>();

    public TcpServer(int port) {
        this.port = port;
    }

    public boolean isServerOn() {
        return serverIsOn;
    }

    /**
     * 开始运行服务器
     */
    public void runServer() {
        serverIsOn = true;
        clientListener = new ClientListener(port);
        clientListenerThreadPool = Executors.newSingleThreadExecutor();
        clientListenerThreadPool.execute(clientListener);

        // 启动心跳检测线程
        ForwardingMessageManager.forwardingThreadPoolExecutor.execute(new HeartBeatMonitor());
    }

    /**
     * 关闭服务器
     */
    public void exitServer() {

        serverIsOn = false;

        clientListenerThreadPool.shutdown();
        clientListener.exit();
        for (ClientHandler clientHandler : clientHandlerMap.values()) {
            clientHandler.exit();
        }
        ForwardingMessageManager.forwardingThreadPoolExecutor.shutdown();
    }

    public int getTotalOnlineCount() {
        return clientHandlerMap.size();
    }

    public static boolean userIsOnline(Long userId) {
        return clientHandlerMap.get(userId) != null && clientHandlerMap.get(userId).isOn;
    }

    /**
     * 发送给全体客户端全网在线人数, 作为心跳检测
     * <p>
     * {
     * "option": "updateOnlineTotal",
     * "totalCount": <全网在线人数>
     * }
     */
    private class HeartBeatMonitor extends Thread {
        @Override
        public void run() {
            while (serverIsOn) {
                for (Long userId : clientHandlerMap.keySet()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<>(10);
                    map.put("option", JsonHelper.Option.UPDATE_ONLINE_TOTAL);
                    map.put("totalCount", getTotalOnlineCount());
                    try {
                        if (clientHandlerMap.get(userId) != null) {
                            synchronized (clientHandlerMap.get(userId)) {
                                clientHandlerMap.get(userId).send(objectMapper.writeValueAsBytes(map));
                            }
                        } else {
                            TcpServer.broadCastUserOnlineStatusToFriends(false, userId);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ClientHandler.ClientReadHandler读取到来自客户端的新消息时的回调方法
     */
    @Override
    public void onNewMessageArrived(byte[] message, Long userId) {
        forwardingMessageManager.forward(message, userId);
    }

    /**
     * 退出ClientHandler
     *
     * @param clientHandler 关闭客户端处理器
     */
    @Override
    public void onExitClientHandler(ClientHandler clientHandler) {
        System.out.println("客户端 " + clientHandler.client.getInetAddress()
                + " p: " + clientHandler.client.getPort() + " 断开了连接");
        clientHandler.isOn = false;

        // 向用户的好友广播其已经下线
        TcpServer.broadCastUserOnlineStatusToFriends(false, clientHandler.userId);
        clientHandler.exit();
        clientHandlerMap.remove(clientHandler.userId);
    }

    /**
     * 用于监听客户端连接的线程
     */
    private class ClientListener extends Thread {

        public void exit() {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private ServerSocket serverSocket;

        ClientListener(int port) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("服务器开始监听客户端连接请求");

            // 客户端
            Socket client;

            // 循环监听客户端的连接请求
            do {
                try {
                    // 获取客户端
                    client = serverSocket.accept();
                    System.out.println("收到客户端连接");

                    // 将新的客户端处理器添加进集合
                    synchronized (TcpServer.this) {
                        // 获取客户端发送过来的userId
                        InputStream inputStream = client.getInputStream();
                        byte[] bytes = new byte[256];

                        // 解析客户端的userId
                        int count = inputStream.read(bytes);
                        long userId = JsonHelper.getUserId(bytes);

                        // 连接成功后构造一个客户端处理器线程, 并传入TcpServer.this用于执行客户端收到消息后的回调方法
                        ClientHandler clientHandler = new ClientHandler(client, TcpServer.this, userId);

                        assert userId != -1;
                        clientHandlerMap.put(userId, clientHandler);

                        System.out.println("用户" + userId + "连接到服务器");
                        TcpServer.broadCastUserOnlineStatusToFriends(true, userId);

                        // 回送连接成功
                        Map<String, Object> map = new HashMap<>(10);
                        map.put("option", JsonHelper.Option.CONNECTION_SUCCESS);
                        client.getOutputStream().write(objectMapper.writeValueAsBytes(map));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    exitServer();
                }
            } while (serverIsOn);
            System.out.println("服务器关闭");
        }
    }

    /**
     * 用户上下线时, 向用户的好友广播该用户状态
     */
    public static void broadCastUserOnlineStatusToFriends(boolean isOnline, Long userId) {
        FriendService friendService = new FriendServiceImpl();
        User user = friendService.searchUser(userId);
        user.setOnline(isOnline ? 1 : 0);
        byte[] bytes = JsonHelper.writeFriendStatusChangedBytes(user);
        ForwardingMessageManager.sendMessage(bytes);
    }

    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer(20000);
        tcpServer.runServer();
    }
}

/**
 * 客户端处理器
 */
class ClientHandler {
    final Socket client;

    /**
     * 读处理器，开一个线程用于读取一个client的消息
     */
    ReadHandler readHandler;

    /**
     * 写处理器
     */
    WriteHandler writeHandler;

    TcpServer clientHandlerCallback;

    /**
     * 客户端用户ID
     */
    Long userId;

    boolean isOn = true;

    ClientHandler(Socket client, TcpServer clientHandlerCallBack, Long userId) {
        this.client = client;
        this.userId = userId;
        this.clientHandlerCallback = clientHandlerCallBack;
        try {
            this.readHandler = new ReadHandler(client.getInputStream());
            this.writeHandler = new WriteHandler(client.getOutputStream());
            this.readHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] bytes) {
        writeHandler.send(bytes);
    }

    public void exit() {
        readHandler.exit();
        writeHandler.exit();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ClientHandlerCallback {
        /**
         * ClientHandler.ClientReadHandler读取到来自客户端的新消息时的回调方法
         *
         * @param message 需要TcpServer转发的字节码, 由JSON转换而来
         * @param userId  客户端的用户ID
         */
        void onNewMessageArrived(byte[] message, Long userId);

        /**
         * 退出ClientHandler
         *
         * @param clientHandler 断开连接的客户端
         */
        void onExitClientHandler(ClientHandler clientHandler);
    }

    /**
     * 客户端读取消息处理器。每读取一条消息就执行ClientHandlerCallBack的回调方法
     */
    private class ReadHandler extends Thread {
        private final InputStream inputStream;

        ReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[256];
            do {
                try {
                    int count = inputStream.read(bytes);
                    if (count > 0) {
                        clientHandlerCallback.onNewMessageArrived(bytes, userId);
                    }
                } catch (Exception e) {
                    if (isOn) {
                        clientHandlerCallback.onExitClientHandler(ClientHandler.this);
                    }
                }
            } while (isOn);
        }

        public void exit() {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写处理器类
     */
    private class WriteHandler {
        private OutputStream outputStream;

        // 多线程写
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        WriteHandler(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        // 发送消息
        private void send(byte[] bytes) {
            if (isOn) {
                executorService.execute(new WriterRunnable(bytes));
            }
        }

        public void exit() {
            try {
                outputStream.close();
                executorService.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private class WriterRunnable implements Runnable {
            private final byte[] bytes;

            WriterRunnable(byte[] bytes) {
                this.bytes = bytes;
            }

            @Override
            public void run() {
                if (isOn) {
                    try {
                        WriteHandler.this.outputStream.write(bytes);
                    } catch (IOException e) {
                        if (isOn) {
                            clientHandlerCallback.onExitClientHandler(ClientHandler.this);
                        }
                    }
                }
            }
        }
    }
}
