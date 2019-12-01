package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Jiang Yinzuo
 */
public class Server {
    private static final int PORT = 20000;

    private static ServerSocket listener = createServerSocket();

    private static Map<Integer, Integer> clientMap = new HashMap<>();

    /**
     * 消息队列
     */
    private static LinkedBlockingQueue<JsonNode> messageQueue = new LinkedBlockingQueue<>(100);

    /**
     * ClientThread线程池
     */
    private static ExecutorService clientThreadPool =
            new ThreadPoolExecutor(10, 20,
                    0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(10),
                    new ClientThreadFactory());

    /**
     * 创建ServerSocket
     *
     * @return ServerSocket
     */
    private static ServerSocket createServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setPerformancePreferences(1, 1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }

    static class Producer implements Runnable {
        private ObjectMapper objectMapper = new ObjectMapper();
        private boolean isOn = true;
        private Socket socket;
        private Integer userId;

        public Producer(Socket socket, Integer userId) {
            this.socket = socket;
            this.userId = userId;
        }

        void produce() throws InterruptedException, IOException {
            byte[] data = new byte[256];
            int count = socket.getInputStream().read(data);
            if (count > 0) {
                JsonNode jsonNode = objectMapper.readTree(data);
                String option = jsonNode.get("option").asText();
                if ("message".equals(option)) {
                    messageQueue.put(jsonNode);
                    clientMap.put(userId, 1);
                } else if ("logout".equals(option)) {
                    isOn = false;
                }
            }
        }

        @Override
        public void run() {
            while (isOn) {
                try {
                    produce();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private ObjectMapper objectMapper = new ObjectMapper();
        private boolean isOn = true;
        private Socket socket;
        private Integer userId;

        public Consumer(Socket socket, Integer userId) {
            this.socket = socket;
            this.userId = userId;
        }

        private void consume() throws InterruptedException {
            synchronized (messageQueue) {
                if (JsonHelper.getMessageSendTo(messageQueue.peek()).equals(userId)) {
                    try {
                        PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                        socketOutput.println("hello" + userId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    notifyAll();
                } else {
                    messageQueue.wait();
                }
            }

        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 为每个客户端连接创建一个线程
     */
    static class ClientHandler implements Runnable {

        // 客户端套接字
        private Socket socket;
        private InputStream inputStream;
        private OutputStream outputStream;
        private Integer clientId;
        private ObjectMapper objectMapper;
        byte[] data = new byte[256];

        public ClientHandler(Socket socket, Integer clientId) {
            this.socket = socket;
            this.clientId = clientId;
            this.objectMapper = new ObjectMapper();
            try {
                this.inputStream = socket.getInputStream();
                this.outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void produce() throws IOException {
            int count = inputStream.read(data);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            boolean isOn = true;


            PrintStream socketOutput = null;
            try {
                socketOutput = new PrintStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            do {
                try {
                    int count = inputStream.read(data);
                    if (count > 0) {
                        JsonNode jsonNode = objectMapper.readTree(data);
                        String option = jsonNode.get("option").asText();
                        if ("message".equals(option)) {
                            socketOutput.println("from: " + jsonNode.get("data").get("sendFrom").asText() +
                                    "content: " + jsonNode.get("data").get("messageContent").asText());
                        } else if ("logout".equals(option)) {
                            socketOutput.println("bye");
                            isOn = false;
                        }
                    } else {
                        socketOutput.println("没有消息");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (isOn);
        }
    }

    static class ClientThreadFactory implements ThreadFactory {
        static Integer clientId;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "thread" + clientId);
        }
    }

    private static void waitForConnection() throws IOException {

        System.out.println("server 启动 " + listener.getInetAddress() + "p: " + listener.getLocalPort());
        while (true) {
            Socket client = listener.accept();
            InputStream inputStream = client.getInputStream();
            byte[] data = new byte[256];
            inputStream.read(data);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
            });
            Integer userId = (Integer)jsonMap.get("userId");
            ClientHandler clientThread = new ClientHandler(client, userId);
            ClientThreadFactory.clientId = userId;
            clientMap.put(userId, 0);
            clientThreadPool.execute(clientThread);
        }
    }

    public static void main(String[] args) {
        try {
            waitForConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
