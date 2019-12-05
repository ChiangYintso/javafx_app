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
     * �������˿ں�
     */
    private final int port;

    /**
     * �����ͻ�������
     */
    private ClientListener clientListener;

    private ForwardingMessageManager forwardingMessageManager = new ForwardingMessageManager(this);

    /**
     * �������ϵĿͻ��˴�����ӳ��
     */
    Map<Integer, ClientHandler> clientHandlerMap = new HashMap<>();

    private TcpServer(int port) {
        this.port = port;
    }

    /**
     * ��ʼ���з�����
     */
    public void run() {
        clientListener = new ClientListener(port);
        // �����ͻ��˼����߳�
        clientListener.start();
    }

    /**
     * ClientHandler.ClientReadHandler��ȡ�����Կͻ��˵�����Ϣʱ�Ļص�����
     */
    @Override
    public void onNewMessageArrived(byte[] message, Integer userId) {
        forwardingMessageManager.forward(message, userId);
    }

    /**
     * �˳�ClientHandler
     *
     * @param userId �رտͻ��˴�������userId
     */
    @Override
    public void onExitClientHandler(Integer userId) {
        clientHandlerMap.remove(userId);
    }

    /**
     * ���ڼ����ͻ������ӵ��߳�
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
                        Integer userId = JsonHelper.getUserId(bytes);

                        // ���ӳɹ�����һ���ͻ��˴������߳�, ������TcpServer.this����ִ�пͻ����յ���Ϣ��Ļص�����
                        ClientHandler clientHandler = new ClientHandler(client, TcpServer.this, userId);

                        clientHandlerMap.put(userId, clientHandler);

                        System.out.println("�û�" + userId + "���ӵ�������");
                        // ����
                        bytes = JsonHelper.sendMessage(1, "�û�" + userId + "������", 123456L, userId.longValue());
                        client.getOutputStream().write(bytes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (isOn);
            System.out.println("�������ر�");
        }
    }

    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer(20000);
        tcpServer.run();
    }
}

/**
 * �ͻ��˴�����
 */
class ClientHandler {
    private final Socket client;

    /**
     * ������������һ���߳����ڶ�ȡһ��client����Ϣ
     */
    private ReadHandler readHandler;

    /**
     * д������
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
         * ClientHandler.ClientReadHandler��ȡ�����Կͻ��˵�����Ϣʱ�Ļص�����
         * @param message ��ҪTcpServerת�����ֽ���, ��JSONת������
         */
        void onNewMessageArrived(byte[] message, Integer userId);

        /**
         * �˳�ClientHandler
         * @param userId �Ͽ����ӵĿͻ��˵�userId
         */
        void onExitClientHandler(Integer userId);
    }

    /**
     * �ͻ��˶�ȡ��Ϣ��������ÿ��ȡһ����Ϣ��ִ��ClientHandlerCallBack�Ļص�����
     */
    private class ReadHandler extends Thread {
        private boolean isOn = true;
        private final InputStream inputStream;
        /**
         * �ص�
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
                        System.out.println("�ͻ��� " + ClientHandler.this.client.getInetAddress()
                                +" p: " + ClientHandler.this.client.getPort() + " �Ͽ�������");
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
     * д��������
     */
    private class WriteHandler {
        private boolean isOn = true;
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
