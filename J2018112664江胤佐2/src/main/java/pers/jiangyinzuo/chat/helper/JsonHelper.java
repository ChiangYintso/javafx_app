package pers.jiangyinzuo.chat.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.domain.entity.Message;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络聊天室的Server和Client之间用Json来传递消息。
 * Json必须包含"option"属性，值可以为"login"、"logout"、"message"等字符串
 *
 * Json格式示例如下:
 * (sendFrom和sendTo放在data里面)
 *
 * {
 *     option: "message | addFriend",
 *     data: {
 *          "sendFrom": 234,
 *          "sendTo": [123, 456]
 *     }
 * }
 *
 * {
 *     option: "login",
 *     userId: 2018112664
 * }
 *
 * @author Jiang Yinzuo
 */
public class JsonHelper {

    /**
     * JSON "option" 属性的值
     */
    public static class Option {
        public static final String MESSAGE = "message";
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String UPDATE_ONLINE_TOTAL = "updateOnlineTotal";
        public static final String ASK_FOR_ONLINE_TOTAL = "askForOnlineTotal";
        public static final String NEW_FRIEND_OR_GROUP = "newFriendOrGroup";
        public static final String ADD_FRIEND = "addFriend";
        public static final String AGREE_TO_ADD_FRIEND = "agreeToAddFriend";
        public static final String JOIN_GROUP = "joinGroup";
        public static final String GROUP_MESSAGE = "groupMessage";
        public static final String CONNECTION_SUCCESS = "connectionSuccess";
    }

    /**
     * 获取Json操作选项
     * @param rawJson 原始JSON字节码
     * @return "message"、"login"、"logout"
     */
    public static String getJsonOption(ObjectMapper objectMapper, byte[] rawJson) {
        try {
            return objectMapper.readTree(rawJson).get("option").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonOption(JsonNode jsonNode) {
        return jsonNode.get("option").asText();
    }

    /**
     * 登录登出
     * @param option "login"或"logout"
     * @param userId 进行登录或登出操作的用户id
     * @return 字节数组
     * @throws JsonProcessingException 转换为Json格式时发生异常
     */
    public static byte[] sendUserId(String option, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", option);
        map.put("userId", userId);
        return objectMapper.writeValueAsBytes(map);
    }

    /**
     * 解析userId
     * @param bytes
     * @return
     */
    public static Integer getUserId(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(bytes).get("userId").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取收件人userId列表
     * @param bytes
     * @return
     */
    public static List<Integer> getSendToList(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> result = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(bytes).get("data").get("sendTo");
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    result.add(node.asInt());
                }
            } else {
                result.add(jsonNode.asInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Integer getSendToId(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(bytes).get("data").get("sendTo").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取发送方id
     * @param jsonNode
     * @return
     */
    public static Integer getSendFromId(JsonNode jsonNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return jsonNode.get("data").get("sendFrom").asInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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
    public static byte[] generateByteMessage(Integer messageType, String messageContent, Long sendFrom, Long sendTo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);

        Message message = new Message.Builder()
                .messageType(messageType)
                .messageContent(messageContent)
                .sendFrom(sendFrom)
                .sendTo(sendTo)
                .sendTime(new Timestamp(System.currentTimeMillis()))
                .build();
        map.put("option", "message");
        map.put("data", message);

        return objectMapper.writeValueAsBytes(map);
    }

    public static byte[] generateNotice(String noticeOption, Long sendFromId, Long sendToId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", noticeOption);

        Map<String, Object> data = new HashMap<>(10);
        data.put("sendFrom", sendFromId);
        data.put("sendTo", sendToId);
        map.put("data", data);
        try {
            return objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getMessageSendTo(JsonNode jsonNode) {
        return jsonNode.get("data").get("sendTo").asInt();
    }
}
