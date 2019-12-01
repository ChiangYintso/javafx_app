package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class Client {

    private Socket socket;
    private OutputStream outputStream;
    private Long userId;

    public Client() throws IOException {
        this(UserState.getSingleton().getUser().getUserId());
    }

    public Client(Long userId) throws IOException {
        this.userId = userId;
        socket = new Socket();

        // 连接本地，端口2000；超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 20000), 3000);
        outputStream = socket.getOutputStream();

        System.out.println("已发起服务器连接，并进入后续流程～");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + " P:" + socket.getPort());

        outputStream.write(sendLoginData(userId));
    }

    public void close() throws IOException {
        socket.close();
    }

    private byte[] sendLoginData(Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", "login");
        map.put("userId", userId);
        return objectMapper.writeValueAsBytes(map);
    }

    private void send() throws IOException {
        // 构建键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到Socket输入流，并转换为BufferedReader
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ObjectMapper objectMapper = new ObjectMapper();
        boolean flag = true;
        do {
            // 键盘读取一行
            String str = input.readLine();

            Map<String, Object> jsonMap = new HashMap<>(10);
            if (!"bye".equals(str)) {
                outputStream.write(JsonHelper.sendMessage(1, str, userId, 2018112664L));
            } else {
                outputStream.write(JsonHelper.writeLogBytes("logout", userId));
            }

            // 从服务器读取一行
            String echo = bufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(2018112667L);
        try {
            // 发送接收数据
            client.send();
        } catch (Exception e) {
            System.out.println("异常关闭");
            e.printStackTrace();
        }

        // 释放资源
        client.close();
        System.out.println("客户端已退出～");
    }
}
