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
 * 网络聊天室的Server和Client之间用Json来传递消息。
 * Json必须包含"option"属性，值可以为"login"、"logout"、"message"等字符串
 * <p>
 * Json格式示例如下:
 * (sendFrom和sendTo放在data里面)
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
     * JSON "option" 属性的值
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
     * 获取Json操作选项
     *
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
     *
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
     * 获取收件人userId列表
     *
     * @param bytes
     * @return
     */
    public static List<Long> getSendToList(byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Long> result = new ArrayList<>();

        try {
            JsonNode jsonNode = objectMapper.readTree(bytes);
            // 获取操作类型
            String option = jsonNode.get("option").asText();

            // 消息
            if (Option.MESSAGE.equals(option)) {
                int messageType = objectMapper.readTree(bytes).get("data").get("messageType").asInt();
                // TODO 更多消息类型的判断
                // 发到群里的消息
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
     * 获取发送方id
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
     * 发消息
     *
     * @param messageType    消息类型
     * @param messageContent 消息内容
     * @param sendFrom       发送方id
     * @param sendTo         好友id或群聊id
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

    /**
     * 获取状态改变的好友
     *
     * @param rawJson 原始JSON数据
     * @return 好友实体类
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
     * 生成JSON通知好友
     * {
     *      "option": "friendStatusChanged",
     *      "friendInfo": <User实体类>,
     *      "data": {
     *      "sendTo": [好友列表],
     *      "sendFrom": <用户>
     * }
     *
     * @param user 状态发送改变的用户实体类
     * @return 字节码
     */
    public static byte[] writeFriendStatusChangedBytes(User user) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);

        // "option"属性
        map.put("option", JsonHelper.Option.FRIEND_STATUS_CHANGED);

        // "friendInfo"属性
        Map<String, Object> userMap = new HashMap<>(10);
        userMap.put("userId", user.getUserId());
        userMap.put("userName", user.getUserName());
        userMap.put("avatar", user.getAvatar());
        userMap.put("isOnline", user.isOnline());
        userMap.put("intro", user.getIntro());
        map.put("friendInfo", userMap);

        // "data"属性
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
