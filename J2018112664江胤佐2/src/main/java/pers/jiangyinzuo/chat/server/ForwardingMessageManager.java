package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.javafx.GuiBroker;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.NoticeService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * ������ת����Ϣ������
 * ת����ͬʱ����MySQL���ݿ�
 *
 * @author Jiang Yinzuo
 */
public class ForwardingMessageManager {
    private static boolean serverGuiIsOn = false;

    private static GuiBroker serverGuiGuiBroker = new GuiBroker();

    public static boolean isServerGuiIsOn() {
        return serverGuiIsOn;
    }

    public static void setServerGuiIsOn(boolean serverGuiIsOn) {
        ForwardingMessageManager.serverGuiIsOn = serverGuiIsOn;
    }

    /**
     * ��Ϣת���̳߳�
     */
    static ExecutorService forwardingThreadPoolExecutor = new ThreadPoolExecutor(20, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(20),
            Executors.defaultThreadFactory());

    private static TcpServer tcpServer;

    private MessageService messageService = new MessageServiceImpl();

    private NoticeService noticeService = new NoticeServiceImpl();

    ForwardingMessageManager(TcpServer tcpServer) {
        ForwardingMessageManager.tcpServer = tcpServer;
    }

    void forward(byte[] message, Long userId) {
        String jsonOption = JsonHelper.getJsonOption(new ObjectMapper(), message);
        System.out.println(jsonOption);

        assert jsonOption != null;
        switch (jsonOption) {
            case JsonHelper.Option.MESSAGE:
                // �����ݿ����������Ϣ��¼
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    messageService.insertMessage(message);
                });
                break;
            case JsonHelper.Option.ADD_FRIEND:
            case JsonHelper.Option.AGREE_TO_ADD_FRIEND:
                // �����ݿ����δ�������Ϣ
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    noticeService.insertNotice(message);
                });
                break;
            case JsonHelper.Option.ASK_FOR_FRIEND_IF_IS_ONLINE:
                /*
                 * �յ�������:
                 * {
                 *      "option": Option.ASK_FOR_FRIEND_IF_IS_ONLINE,
                 *      "sendFrom": <����ѯ�ʵĿͻ���id>,
                 *      "friendIdList": <�����б�>
                 * }
                 *
                 * ���ص�����:
                 * {
                 *      "option": Option.FRIENDS_ONLINE_STATUS,
                 *      "sendTo": <���͸��ͻ��˵�id>,
                 *      "onLineList": <���ߵĺ���id>
                 * }
                 */
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode root = objectMapper.readTree(message);
                        JsonNode friendIdList = root.get("friendIdList");
                        assert friendIdList.isArray();
                        Map<String, Object> rawJson = new HashMap<>(10);
                        rawJson.put("option", JsonHelper.Option.FRIENDS_ONLINE_STATUS);
                        long sendToId = (long) root.get("sendFrom").asInt();
                        rawJson.put("sendTo", sendToId);

                        List<Long> onLineList = new ArrayList<>();

                        for (JsonNode friendIdJsonNode : friendIdList) {
                            long friendId = friendIdJsonNode.asInt();
                            if (tcpServer.clientHandlerMap.get(friendId) != null) {
                                onLineList.add(friendId);
                            }
                        }
                        rawJson.put("onLineList", onLineList);
                        tcpServer.clientHandlerMap.get(sendToId).send(objectMapper.writeValueAsBytes(rawJson));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return;
            case JsonHelper.Option.FOUND_GROUP:

                // �������ͼ�ν����ѿ���, ֪ͨ�����ͼ�ν���
                if (serverGuiIsOn) {
                    serverGuiGuiBroker.onNewMessageArrived(message, userId);
                }
                return;
            default:
                break;
        }

        sendMessage(message);
    }

    public static void sendMessage(byte[] message) {
        // ������Ϣ���û�
        ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
            List<Long> sendToList = JsonHelper.getSendToList(message);
            ClientHandler clientHandler;
            for (Long sendToUserId : sendToList) {
                clientHandler = tcpServer.clientHandlerMap.get(sendToUserId);

                // �û��Ѿ�����
                if (clientHandler != null) {
                    clientHandler.send(message);
                }
            }
        });
    }

}
