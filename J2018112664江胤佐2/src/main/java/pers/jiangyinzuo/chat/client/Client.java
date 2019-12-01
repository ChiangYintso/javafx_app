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

        // ���ӱ��أ��˿�2000����ʱʱ��3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 20000), 3000);
        outputStream = socket.getOutputStream();

        System.out.println("�ѷ�����������ӣ�������������̡�");
        System.out.println("�ͻ�����Ϣ��" + socket.getLocalAddress() + " P:" + socket.getLocalPort());
        System.out.println("��������Ϣ��" + socket.getInetAddress() + " P:" + socket.getPort());

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
        // ��������������
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // �õ�Socket����������ת��ΪBufferedReader
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ObjectMapper objectMapper = new ObjectMapper();
        boolean flag = true;
        do {
            // ���̶�ȡһ��
            String str = input.readLine();

            Map<String, Object> jsonMap = new HashMap<>(10);
            if (!"bye".equals(str)) {
                outputStream.write(JsonHelper.sendMessage(1, str, userId, 2018112664L));
            } else {
                outputStream.write(JsonHelper.writeLogBytes("logout", userId));
            }

            // �ӷ�������ȡһ��
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
            // ���ͽ�������
            client.send();
        } catch (Exception e) {
            System.out.println("�쳣�ر�");
            e.printStackTrace();
        }

        // �ͷ���Դ
        client.close();
        System.out.println("�ͻ������˳���");
    }
}
