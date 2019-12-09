package pers.jiangyinzuo.chat.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���������ҵ�Server��Client֮����Json��������Ϣ��
 * Json�������"option"���ԣ�ֵ����Ϊ"login"��"logout"��"message"���ַ���
 * <p>
 * Json��ʽʾ������:
 * (sendFrom��sendTo����data����)
 * <p>
 * {
 * option: "message | addFriend",
 * data: {
 * "sendFrom": 234,
 * "sendTo": [123, 456]
 * }
 * }
 * <p>
 * {
 * option: "login",
 * userId: 2018112664
 * }
 *
 * @author Jiang Yinzuo
 */
public class JsonHelper {

    /**
     * JSON "option" ���Ե�ֵ
     */
    public static class Option {
        public static final String FOUND_GROUP = "foundGroup";
        public static final String MESSAGE = "message";
        public static final String UPDATE_ONLINE_TOTAL = "updateOnlineTotal";
        public static final String NEW_FRIEND_OR_GROUP = "newFriendOrGroup";
        public static final String ADD_FRIEND = "addFriend";
        public static final String AGREE_TO_ADD_FRIEND = "agreeToAddFriend";
        public static final String JOIN_GROUP = "joinGroup";
        public static final String GROUP_MESSAGE = "groupMessage";
        public static final String CONNECTION_SUCCESS = "connectionSuccess";
        public static final String FRIEND_STATUS_CHANGED = "friendStatusChanged";
        public static final String GROUP_STATUS_CHANGED = "groupStatusChanged";
        public static final String ASK_FOR_FRIEND_IF_IS_ONLINE = "askForFriendIfIsOnline";
        public static final String FRIENDS_ONLINE_STATUS = "friendsOnlineStatus";
        public static final String FOUND_GROUP_ACCEPTED = "foundGroupAccepted";
    }

    /**
     * ��ȡJson����ѡ��
     *
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
     *
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
     *
     * @param bytes
     * @return
     */
    public static long getUserId(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(bytes).get("userId").asInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getMessageType(JsonNode jsonNode) {
        return jsonNode.get("data").get("messageType").asInt();
    }

    /**
     * ��ȡ�ռ���userId�б�
     *
     * @param bytes
     * @return
     */
    public static List<Long> getSendToList(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> result = new ArrayList<>();

        try {
            JsonNode jsonNode = objectMapper.readTree(bytes);
            // ��ȡ��������
            String option = jsonNode.get("option").asText();

            // ��Ϣ
            if (Option.MESSAGE.equals(option)) {
                int messageType = objectMapper.readTree(bytes).get("data").get("messageType").asInt();
                // TODO ������Ϣ���͵��ж�
                // ����Ⱥ�����Ϣ
                if (!Message.isFriendMessage(messageType)) {
                    GroupService groupService = new GroupServiceImpl();
                    List<Long> userIdList = groupService.getUserIdsInGroup(objectMapper.readTree(bytes).get("data").get("sendTo").asLong());
                    userIdList.remove(JsonHelper.getSendFromId(jsonNode));
                    return userIdList;
                }
            }

            JsonNode sendToJsonNode = objectMapper.readTree(bytes).get("data").get("sendTo");
            if (sendToJsonNode.isArray()) {
                for (JsonNode node : sendToJsonNode) {
                    result.add((long) node.asInt());
                }
            } else {
                result.add((long) sendToJsonNode.asInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long getSendToGroupId(JsonNode jsonNode) {
        return jsonNode.get("data").get("sendTo").asInt();
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
     *
     * @param jsonNode
     * @return
     */
    public static long getSendFromId(JsonNode jsonNode) {
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
     *
     * @param messageType    ��Ϣ����
     * @param messageContent ��Ϣ����
     * @param sendFrom       ���ͷ�id
     * @param sendTo         ����id��Ⱥ��id
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

    /**
     * ��ȡ״̬�ı�ĺ���
     *
     * @param rawJson ԭʼJSON����
     * @return ����ʵ����
     */
    public static User getStatusChangedUser(JsonNode rawJson) {
        JsonNode friendInfo = rawJson.get("friendInfo");
        long userId = friendInfo.get("userId").asInt();
        String userName = friendInfo.get("userName").asText();
        String avatar = friendInfo.get("avatar").asText();
        Integer isOnline = friendInfo.get("isOnline").asInt();
        String intro = friendInfo.get("intro").asText();
        return new User.Builder()
                .userId(userId)
                .userName(userName)
                .avatar(avatar)
                .isOnline(isOnline)
                .intro(intro)
                .build();
    }

    /**
     * ����JSON֪ͨ����
     * {
     *      "option": "friendStatusChanged",
     *      "friendInfo": <Userʵ����>,
     *      "data": {
     *      "sendTo": [�����б�],
     *      "sendFrom": <�û�>
     * }
     *
     * @param user ״̬���͸ı���û�ʵ����
     * @return �ֽ���
     */
    public static byte[] writeFriendStatusChangedBytes(User user) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);

        // "option"����
        map.put("option", JsonHelper.Option.FRIEND_STATUS_CHANGED);

        // "friendInfo"����
        Map<String, Object> userMap = new HashMap<>(10);
        userMap.put("userId", user.getUserId());
        userMap.put("userName", user.getUserName());
        userMap.put("avatar", user.getAvatar());
        userMap.put("isOnline", user.isOnline());
        userMap.put("intro", user.getIntro());
        map.put("friendInfo", userMap);

        // "data"����
        Map<String, Object> data = new HashMap<>(10);
        data.put("sendFrom", user.getUserId());
        data.put("sendTo", user.getFriendIdList());
        map.put("data", data);

        byte[] bytes = new byte[256];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
