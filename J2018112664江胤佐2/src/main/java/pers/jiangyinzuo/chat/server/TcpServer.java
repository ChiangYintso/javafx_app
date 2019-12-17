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
     * �������˿ں�
     */
    private final int port;

    /**
     * �����ͻ�������
     */
    private ClientListener clientListener;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ForwardingMessageManager forwardingMessageManager = ForwardingMessageManager.newForwardingMessageManager(this);

    private ExecutorService clientListenerThreadPool;

    private boolean serverIsOn = false;
    /**
     * �������ϵĿͻ��˴�����ӳ��
     */
    static Map<Long, ClientHandler> clientHandlerMap = new HashMap<>();

    public TcpServer(int port) {
        this.port = port;
    }

    public boolean isServerOn() {
        return serverIsOn;
    }

    /**
     * ��ʼ���з�����
     */
    public void runServer() {
        serverIsOn = true;
        clientListener = new ClientListener(port);
        clientListenerThreadPool = Executors.newSingleThreadExecutor();
        clientListenerThreadPool.execute(clientListener);

        // ������������߳�
        ForwardingMessageManager.forwardingThreadPoolExecutor.execute(new HeartBeatMonitor());
    }

    /**
     * �رշ�����
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
     * ���͸�ȫ��ͻ���ȫ����������, ��Ϊ�������
     * <p>
     * {
     * "option": "updateOnlineTotal",
     * "totalCount": <ȫ����������>
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
     * ClientHandler.ClientReadHandler��ȡ�����Կͻ��˵�����Ϣʱ�Ļص�����
     */
    @Override
    public void onNewMessageArrived(byte[] message, Long userId) {
        forwardingMessageManager.forward(message, userId);
    }

    /**
     * �˳�ClientHandler
     *
     * @param clientHandler �رտͻ��˴�����
     */
    @Override
    public void onExitClientHandler(ClientHandler clientHandler) {
        System.out.println("�ͻ��� " + clientHandler.client.getInetAddress()
                + " p: " + clientHandler.client.getPort() + " �Ͽ�������");
        clientHandler.isOn = false;

        // ���û��ĺ��ѹ㲥���Ѿ�����
        TcpServer.broadCastUserOnlineStatusToFriends(false, clientHandler.userId);
        clientHandler.exit();
        clientHandlerMap.remove(clientHandler.userId);
    }

    /**
     * ���ڼ����ͻ������ӵ��߳�
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
            System.out.println("��������ʼ�����ͻ�����������");

            // �ͻ���
            Socket client;

            // ѭ�������ͻ��˵���������
            do {
                try {
                    // ��ȡ�ͻ���
                    client = serverSocket.accept();
                    System.out.println("�յ��ͻ�������");

                    // ���µĿͻ��˴�������ӽ�����
                    synchronized (TcpServer.this) {
                        // ��ȡ�ͻ��˷��͹�����userId
                        InputStream inputStream = client.getInputStream();
                        byte[] bytes = new byte[256];

                        // �����ͻ��˵�userId
                        int count = inputStream.read(bytes);
                        long userId = JsonHelper.getUserId(bytes);

                        // ���ӳɹ�����һ���ͻ��˴������߳�, ������TcpServer.this����ִ�пͻ����յ���Ϣ��Ļص�����
                        ClientHandler clientHandler = new ClientHandler(client, TcpServer.this, userId);

                        assert userId != -1;
                        clientHandlerMap.put(userId, clientHandler);

                        System.out.println("�û�" + userId + "���ӵ�������");
                        TcpServer.broadCastUserOnlineStatusToFriends(true, userId);

                        // �������ӳɹ�
                        Map<String, Object> map = new HashMap<>(10);
                        map.put("option", JsonHelper.Option.CONNECTION_SUCCESS);
                        client.getOutputStream().write(objectMapper.writeValueAsBytes(map));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    exitServer();
                }
            } while (serverIsOn);
            System.out.println("�������ر�");
        }
    }

    /**
     * �û�������ʱ, ���û��ĺ��ѹ㲥���û�״̬
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
 * �ͻ��˴�����
 */
class ClientHandler {
    final Socket client;

    /**
     * ������������һ���߳����ڶ�ȡһ��client����Ϣ
     */
    ReadHandler readHandler;

    /**
     * д������
     */
    WriteHandler writeHandler;

    TcpServer clientHandlerCallback;

    /**
     * �ͻ����û�ID
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
         * ClientHandler.ClientReadHandler��ȡ�����Կͻ��˵�����Ϣʱ�Ļص�����
         *
         * @param message ��ҪTcpServerת�����ֽ���, ��JSONת������
         * @param userId  �ͻ��˵��û�ID
         */
        void onNewMessageArrived(byte[] message, Long userId);

        /**
         * �˳�ClientHandler
         *
         * @param clientHandler �Ͽ����ӵĿͻ���
         */
        void onExitClientHandler(ClientHandler clientHandler);
    }

    /**
     * �ͻ��˶�ȡ��Ϣ��������ÿ��ȡһ����Ϣ��ִ��ClientHandlerCallBack�Ļص�����
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
     * д��������
     */
    private class WriteHandler {
        private OutputStream outputStream;

        // ���߳�д
        private final ExecutorService executorService = Executors.newSingleThreadExecutor();

        WriteHandler(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        // ������Ϣ
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
