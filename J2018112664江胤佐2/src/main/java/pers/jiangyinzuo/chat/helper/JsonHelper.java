package pers.jiangyinzuo.chat.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.domain.entity.Message;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络聊天室的Server和Client之间用Json来传递消息。
 * Json必须包含"option"属性，值可以为"login"、"logout"、"message"等字符串
 * @author Jiang Yinzuo
 */
public class JsonHelper {

    /**
     * 登录登出
     * @param option "login"或"logout"
     * @param userId 进行登录或登出操作的用户id
     * @return 字节数组
     * @throws JsonProcessingException 转换为Json格式时发生异常
     */
    public static byte[] writeLogBytes(String option, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", option);
        map.put("userId", userId);
        return objectMapper.writeValueAsBytes(map);
    }

    /**
     * 发消息
     * @param messageType 消息类型
     * @param messageContent 消息内容
     * @param sendFrom 发送方id
     * @param sendTo 接收方id
     * @return 字节数组
     * @throws JsonProcessingException 转换为Json格式时发生异常
     */
    public static byte[] sendMessage(Integer messageType, String messageContent, Long sendFrom, Long sendTo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        Message message = new Message.Builder()
                .messageType(messageType)
                .messageContent(messageContent)
                .sendFrom(sendFrom)
                .sendTo(sendTo)
                .sendTime(new Timestamp(0))
                .build();
        map.put("option", "message");
        map.put("data", message);

        return objectMapper.writeValueAsBytes(map);
    }

    public static Integer getMessageSendTo(JsonNode jsonNode) {
        return jsonNode.get("data").get("userId").asInt();
    }
}
