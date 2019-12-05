package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 读取消息一个线程、发送消息一个线程
 * @author Jiang Yinzuo
 */
public class TcpClient {
    /**
     * 套接字
     */
    private final Socket socket;

    /**
     * 读消息处理器
     */
    private ReadHandler readHandler;

    private final Long userId;

    /**
     * 输出流
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
            System.out.println("已发起服务器连接");
            System.out.println("客户端信息：" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
            System.out.println("服务器信息：" + socket.getInetAddress() + " P:" + socket.getPort());
            this.readHandler = new ReadHandler(socket.getInputStream());
            this.outputStream = socket.getOutputStream();
        } catch (Exception e) {
            System.out.println("连接失败");
            this.isOn = false;
        }
    }

    /**
     * 开启连接
     * @return TcpClient对象
     */
    public void run() {
        if (isOn) {
            try {
                // 开启监听消息线程
                ReadHandler readHandler = new ReadHandler(socket.getInputStream());
                readHandler.start();

                // 发送userId
                sendUserId(socket.getOutputStream(), this.userId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接服务器时发送一份userId作为服务器ClientHandler标识
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
     * 向服务器发送消息
     * @param data 需要发送的字节码
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
     * 客户端读取服务端消息线程
     */
    private class ReadHandler extends Thread {
        /**
         * 输入流
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
                        System.out.println("服务器关闭");
                        TcpClient.this.exit();
                    }
                }
            } while (TcpClient.this.isOn);
        }
    }

    /**
     * 断开客户端连接
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
                System.out.println("请输入收件人id");
                Long sendTo = Long.parseLong(input.readLine());
                System.out.println("输入收件内容");
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
