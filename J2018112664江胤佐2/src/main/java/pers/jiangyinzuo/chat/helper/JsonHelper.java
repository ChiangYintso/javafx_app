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
 * ���������ҵ�Server��Client֮����Json��������Ϣ��
 * Json�������"option"���ԣ�ֵ����Ϊ"login"��"logout"��"message"���ַ���
 *
 * Json��ʽʾ������:
 * (sendFrom��sendTo����data����)
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
     * JSON "option" ���Ե�ֵ
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
     * ��ȡJson����ѡ��
     * @param rawJson ԭʼJSON�ֽ���
     * @return "message"��"login"��"logout"
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
     * ��¼�ǳ�
     * @param option "login"��"logout"
     * @param userId ���е�¼��ǳ��������û�id
     * @return �ֽ�����
     * @throws JsonProcessingException ת��ΪJson��ʽʱ�����쳣
     */
    public static byte[] sendUserId(String option, Long userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", option);
        map.put("userId", userId);
        return objectMapper.writeValueAsBytes(map);
    }

    /**
     * ����userId
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
     * ��ȡ�ռ���userId�б�
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
     * ��ȡ���ͷ�id
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
     * ����Ϣ
     * @param messageType ��Ϣ����
     * @param messageContent ��Ϣ����
     * @param sendFrom ���ͷ�id
     * @param sendTo ���շ�id
     * @return �ֽ�����
     * @throws JsonProcessingException ת��ΪJson��ʽʱ�����쳣
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
