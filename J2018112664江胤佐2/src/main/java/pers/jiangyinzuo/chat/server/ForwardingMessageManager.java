package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;
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
 * 服务器转发消息管理类
 * 转发的同时存入MySQL数据库
 *
 * @author Jiang Yinzuo
 */
public class ForwardingMessageManager {

    /**
     * 消息转发线程池
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
                // 向数据库存入聊天消息记录
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    messageService.insertMessage(message);
                });
                break;
            case JsonHelper.Option.ADD_FRIEND:
            case JsonHelper.Option.AGREE_TO_ADD_FRIEND:
                // 向数据库存入未处理的消息
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    noticeService.insertNotice(message);
                });
                break;
            case JsonHelper.Option.ASK_FOR_FRIEND_IF_IS_ONLINE:
                /*
                 * 收到的数据:
                 * {
                 *      "option": Option.ASK_FOR_FRIEND_IF_IS_ONLINE,
                 *      "sendFrom": <发起询问的客户端id>,
                 *      "friendIdList": <好友列表>
                 * }
                 *
                 * 返回的数据:
                 * {
                 *      "option": Option.FRIENDS_ONLINE_STATUS,
                 *      "sendTo": <发送给客户端的id>,
                 *      "onLineList": <上线的好友id>
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
            default:
                break;
        }

        sendMessage(message);
    }

    public static void sendMessage(byte[] message) {
        // 发送消息给用户
        ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
            List<Long> sendToList = JsonHelper.getSendToList(message);
            ClientHandler clientHandler;
            for (Long sendToUserId : sendToList) {
                clientHandler = tcpServer.clientHandlerMap.get(sendToUserId);

                // 用户已经上线
                if (clientHandler != null) {
                    clientHandler.send(message);
                }
            }
        });
    }

}
