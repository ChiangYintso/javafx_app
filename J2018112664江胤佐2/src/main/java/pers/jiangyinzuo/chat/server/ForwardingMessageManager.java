package pers.jiangyinzuo.chat.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.NoticeService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 服务器转发消息管理类
 * @author Jiang Yinzuo
 */
public class ForwardingMessageManager {

    /**
     * 消息转发线程池
     *
     */
    static ExecutorService forwardingThreadPoolExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(20),
            Executors.defaultThreadFactory());

    private final TcpServer tcpServer;

    private MessageService messageService = new MessageServiceImpl();

    private NoticeService noticeService = new NoticeServiceImpl();

    ForwardingMessageManager(TcpServer tcpServer) {
        this.tcpServer = tcpServer;
    }

    void forward(byte[] message, Integer userId) {
        String jsonOption = JsonHelper.getJsonOption(new ObjectMapper(), message);
        System.out.println(jsonOption);

        assert jsonOption != null;
        switch (jsonOption) {
            case JsonHelper.Option.ASK_FOR_ONLINE_TOTAL:
                /*
                  向全体客户端发送消息, 用于更新全网在线人数
                  {
                      "option": "updateOnlineTotal",
                      "totalCount": <全网在线人数>
                  }
                */
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = new HashMap<>(10);
                    map.put("option", JsonHelper.Option.UPDATE_ONLINE_TOTAL);
                    map.put("totalCount", tcpServer.clientHandlerMap.size());
                    try {
                        synchronized (tcpServer.clientHandlerMap.get(userId)) {
                            tcpServer.clientHandlerMap.get(userId).send(objectMapper.writeValueAsBytes(map));
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
                return;
            case JsonHelper.Option.MESSAGE:
                // 向数据库存入聊天消息记录
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    messageService.insertMessage(message);
                });
                break;
            case JsonHelper.Option.ADD_FRIEND:
                // 向数据库存入未处理的消息
                ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
                    noticeService.insertNotice(message);
                });
                break;
            default:
                break;
        }

        // 发送消息给用户
        ForwardingMessageManager.forwardingThreadPoolExecutor.execute(() -> {
            synchronized (this.tcpServer) {
                List<Integer> sendToList = JsonHelper.getSendToList(message);
                ClientHandler clientHandler;
                for (Integer sendToUserId : sendToList) {
                    clientHandler = tcpServer.clientHandlerMap.get(sendToUserId);

                    // 用户已经上线
                    if (clientHandler != null) {
                        clientHandler.send(message);
                    }
                    // TODO: 用户未上线
                }
            }
        });
    }
}
