package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * ��ȡ��Ϣһ���̡߳�������Ϣһ���߳�
 * @author Jiang Yinzuo
 */
public class TcpClient {
    /**
     * �׽���
     */
    private final Socket socket;

    /**
     * ����Ϣ������
     */
    private ReadHandler readHandler;

    private final Long userId;

    /**
     * �����
     */
    private OutputStream outputStream;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Broker broker = new Broker();

    private boolean isOn = true;

    public TcpClient(Long userId) {
        socket = new Socket();
        this.userId = userId;
        try {
            socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 20000), 3000);
            System.out.println("�ѷ������������");
            System.out.println("�ͻ�����Ϣ��" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
            System.out.println("��������Ϣ��" + socket.getInetAddress() + " P:" + socket.getPort());
            this.readHandler = new ReadHandler(socket.getInputStream());
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            System.out.println("����ʧ��");
            this.isOn = false;
        }
    }

    /**
     * ��������
     * @return TcpClient����
     */
    public void run() {
        if (isOn) {
            try {
                // ����������Ϣ�߳�
                ReadHandler readHandler = new ReadHandler(socket.getInputStream());
                readHandler.start();

                // ����userId
                sendUserId(socket.getOutputStream(), this.userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ���ӷ�����ʱ����һ��userId��Ϊ������ClientHandler��ʶ
     */
    private static void sendUserId(OutputStream outputStream, Long userId) {
        try {
            byte[] bytes = JsonHelper.sendUserId("login", userId);
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMessage(JsonNode jsonNode) {
        try {
            this.outputStream.write(objectMapper.writeValueAsBytes(jsonNode));
            this.outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �������������Ϣ
     * @param data ��Ҫ���͵��ֽ���
     */
    public void sendMessage(byte[] data) {
        try {
            this.outputStream.write(data);
//            this.outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ͻ��˶�ȡ�������Ϣ�߳�
     */
    private class ReadHandler extends Thread {
        /**
         * ������
         */
        private final InputStream inputStream;

        private ObjectMapper objectMapper;

        ReadHandler(InputStream inputStream) {
            this.inputStream = inputStream;
            objectMapper = new ObjectMapper();
        }

        void exit() {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            byte[] data = new byte[256];
            do {
                try {
                    int count = inputStream.read(data);
                    if (count > 0) {
                        broker.receiveMessage(objectMapper.readTree(data));
                    }
                } catch (Exception e) {
                    if (TcpClient.this.isOn) {
                        System.out.println("�������ر�");
                        TcpClient.this.exit();
                    }
                }
            } while (TcpClient.this.isOn);
        }
    }

    /**
     * �Ͽ��ͻ�������
     */
    public void exit() {
        try {
            outputStream.close();
            readHandler.exit();
            socket.close();
            isOn = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void keyBoardWrite() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (isOn) {
            try {
                System.out.println("�������ռ���id");
                Long sendTo = Long.parseLong(input.readLine());
                System.out.println("�����ռ�����");
                String str = input.readLine();
                sendMessage(JsonHelper.sendMessage(1, str, userId, sendTo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TcpClient tcpClient = new TcpClient(2018112664L);
        tcpClient.run();

        tcpClient.keyBoardWrite();
    }
}
