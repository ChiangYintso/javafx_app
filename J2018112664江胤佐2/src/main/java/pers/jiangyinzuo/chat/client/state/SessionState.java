package pers.jiangyinzuo.chat.client.state;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.ChattingBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �Ự״̬��
 *
 * @author Jiang Yinzuo
 */
public class SessionState {

    public interface Subscriber {
        /**
         * �µĺ�����Ϣ����
         *
         * @param rawJson ������Ϣ
         */
        void onNewMessageArrived(JsonNode rawJson);

        /**
         * ���ѻ�Ⱥ�ĵ�״̬�����ı�
         * @param rawJson ԭʼJSON����
         */
        void onStatusChanged(JsonNode rawJson);

        /**
         * ע���Ϊ������
         */
        void registerAsSubscriber();
    }

    private static SessionCardCmpController.Session selectedSession;

    /**
     * ������
     */
    private static Map<Long, SessionCardCmpController> friendSessionCardCmpDistributorMap = new ConcurrentHashMap<>(20);
    private static Map<Long, SessionCardCmpController> groupSessionCardCmpDistributorMap = new ConcurrentHashMap<>(20);
    private static Map<Long, ChattingBoardController> friendChattingBoardDistributorMap = new ConcurrentHashMap<>(20);
    private static Map<Long, ChattingBoardController> groupChattingBoardDistributorMap = new ConcurrentHashMap<>(20);

    public synchronized static SessionCardCmpController.Session getSelectedSession() {
        return selectedSession;
    }

    public synchronized static void setSelectedSession(SessionCardCmpController.Session selectedSession) {
        SessionState.selectedSession = selectedSession;
    }

    public static void addToFriendSessionCardCmpDistributorMap(Long sendFromId, SessionCardCmpController controller) {
        friendSessionCardCmpDistributorMap.put(sendFromId, controller);
    }

    public static void addToGroupSessionCardCmpDistributorMap(Long groupId, SessionCardCmpController controller) {
        groupSessionCardCmpDistributorMap.put(groupId, controller);
    }

    public static void addToFriendChattingBoardSubscriberMap(Long sendFromId, ChattingBoardController controller) {
        friendChattingBoardDistributorMap.put(sendFromId, controller);
    }

    public static void addToGroupChattingBoardSubscriberMap(Long sendFromId, ChattingBoardController controller) {
        groupChattingBoardDistributorMap.put(sendFromId, controller);
    }

    /**
     * TcpClient�յ���Ϣ��֪ͨ��Ӧ�ĺ��ѻỰ
     *
     * @param jsonNode ��Ϣ
     */
    public static void notifySession(JsonNode jsonNode) {
        String option = JsonHelper.getJsonOption(jsonNode);
        switch (option) {
            case JsonHelper.Option.MESSAGE:
                int messageType = JsonHelper.getMessageType(jsonNode);

                if (Message.isFriendMessage(messageType)) {
                    notifyFriendSession(jsonNode, option);
                } else {
                    notifyGroupSession(jsonNode, option);
                }
                break;
            case JsonHelper.Option.FRIEND_STATUS_CHANGED:
                notifyFriendSession(jsonNode, option);
                break;
            case JsonHelper.Option.GROUP_STATUS_CHANGED:
                notifyGroupSession(jsonNode, option);
                break;
            default:
                break;
        }
    }

    public static void notifyOnlineStatus(JsonNode rawJson) {
        if (rawJson.get("onLineList").isArray()) {
            for (JsonNode jsonNode : rawJson.get("onLineList")) {
                friendSessionCardCmpDistributorMap.get((long)jsonNode.asInt()).changeOnlineStatus();
            }
        }
    }

    private static void notifyGroupSession(JsonNode jsonNode, String option) {
        // TODO ����optionִ�в�ͬ���߼�
        long groupId = JsonHelper.getSendToGroupId(jsonNode);

        // ֪ͨ�Ự�б�
        SessionCardCmpController sessionCardCmpController =
                groupSessionCardCmpDistributorMap.get(groupId);
        assert sessionCardCmpController != null;
        sessionCardCmpController.onNewMessageArrived(jsonNode);

		// ֪ͨ�������
		ChattingBoardController chattingBoardController =
				groupChattingBoardDistributorMap.get(groupId);
		if (chattingBoardController != null) {
			chattingBoardController.onNewMessageArrived(jsonNode);
		}
    }

    private static void notifyFriendSession(JsonNode jsonNode, String option) {
        Long sendFromId = JsonHelper.getSendFromId(jsonNode);

        // ��ȡ�Ự��ƬController
        SessionCardCmpController sessionCardCmpController =
                friendSessionCardCmpDistributorMap.get(sendFromId);
        assert sessionCardCmpController != null;

        // ��ȡ�������Controller
        ChattingBoardController chattingBoardController =
                friendChattingBoardDistributorMap.get(sendFromId);

        switch (option) {
            case JsonHelper.Option.MESSAGE:
                sessionCardCmpController.onNewMessageArrived(jsonNode);
                if (chattingBoardController != null) {
                    chattingBoardController.onNewMessageArrived(jsonNode);
                }
                break;
            case JsonHelper.Option.FRIEND_STATUS_CHANGED:
                sessionCardCmpController.onStatusChanged(jsonNode);
                if (chattingBoardController != null) {
                    chattingBoardController.onStatusChanged(jsonNode);
                }
                break;
            default:
                break;
        }

    }
}
