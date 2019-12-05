package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option;

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

    private ForwardingMessageManager forwardingMessageManager = new ForwardingMessageManager(this);

    /**
     * 已连接上的客户端处理器映射
     */
    Map<Integer, ClientHandler> clientHandlerMap = new HashMap<>();

    private TcpServer(int port) {
        this.port = port;
    }

    /**
     * 开始运行服务器
     */
    public void run() {
        clientListener = new ClientListener(port);
        // 启动客户端监听线程
        clientListener.start();
    }

    /**
     * ClientHandler.ClientReadHandler读取到来自客户端的新消息时的回调方法
     */
    @Override
    public void onNewMessageArrived(byte[] message, Integer userId) {
        forwardingMessageManager.forward(message, userId);
    }

    /**
     * 退出ClientHandler
     *
     * @param userId 关闭客户端处理器的userId
     */
    @Override
    public void onExitClientHandler(Integer userId) {
        clientHandlerMap.remove(userId);
    }

    /**
     * 用于监听客户端连接的线程
     */
    private class ClientListener extends Thread {
        private boolean isOn = true;
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
                        Integer userId = JsonHelper.getUserId(bytes);

                        // 连接成功后构造一个客户端处理器线程, 并传入TcpServer.this用于执行客户端收到消息后的回调方法
                        ClientHandler clientHandler = new ClientHandler(client, TcpServer.this, userId);

                        clientHandlerMap.put(userId, clientHandler);

                        System.out.println("用户" + userId + "连接到服务器");
                        // 回送
                        bytes = JsonHelper.sendMessage(1, "用户" + userId + "已连接", 123456L, userId.longValue());
                        client.getOutputStream().write(bytes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (isOn);
            System.out.println("服务器关闭");
        }
    }

    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer(20000);
        tcpServer.run();
    }
}

/**
 * 客户端处理器
 */
class ClientHandler {
    private final Socket client;

    /**
     * 读处理器，开一个线程用于读取一个client的消息
     */
    private ReadHandler readHandler;

    /**
     * 写处理器
     */
    private WriteHandler writeHandler;

    private Integer userId;

    ClientHandler(Socket client, TcpServer clientHandlerCallBack, Integer userId) {
        this.client = client;
        this.userId = userId;
        try {
            this.readHandler = new ReadHandler(client.getInputStream(), clientHandlerCallBack);
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
         * @param message 需要TcpServer转发的字节码, 由JSON转换而来
         */
        void onNewMessageArrived(byte[] message, Integer userId);

        /**
         * 退出ClientHandler
         * @param userId 断开连接的客户端的userId
         */
        void onExitClientHandler(Integer userId);
    }

    /**
     * 客户端读取消息处理器。每读取一条消息就执行ClientHandlerCallBack的回调方法
     */
    private class ReadHandler extends Thread {
        private boolean isOn = true;
        private final InputStream inputStream;
        /**
         * 回调
         */
        private final ClientHandlerCallback clientHandlerCallBack;

        ReadHandler(InputStream inputStream, ClientHandlerCallback clientHandlerCallBack) {
            this.inputStream = inputStream;
            this.clientHandlerCallBack = clientHandlerCallBack;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[256];
            do {
                try {
                    int count = inputStream.read(bytes);
                    if (count > 0) {
                        clientHandlerCallBack.onNewMessageArrived(bytes, userId);
                    }
                } catch (Exception e) {
                    if (isOn) {
                        System.out.println("客户端 " + ClientHandler.this.client.getInetAddress()
                                +" p: " + ClientHandler.this.client.getPort() + " 断开了连接");
                        isOn = false;
                        ClientHandler.this.exit();
                        clientHandlerCallBack.onExitClientHandler(ClientHandler.this.userId);
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
        private boolean isOn = true;
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
                if (WriteHandler.this.isOn) {
                    try {
                        WriteHandler.this.outputStream.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
