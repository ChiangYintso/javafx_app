package pers.jiangyinzuo.chat.client.state;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.ChattingBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �Ự״̬��
 * @author Jiang Yinzuo
 *
 */
public class SessionState {

	public interface Subscriber {
		/**
		 * �µĺ�����Ϣ����
		 * @param rawJson ������Ϣ
		 */
		void onNewFriendMessageArrived(JsonNode rawJson);

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
	 * @param jsonNode ��Ϣ
	 */
	public static void notifyFriendSession(JsonNode jsonNode) {
		Long sendFromId = JsonHelper.getSendFromId(jsonNode).longValue();

		// ֪ͨ�Ự�б�
		SessionCardCmpController sessionCardCmpController =
				friendSessionCardCmpDistributorMap.get(sendFromId);
		if (sessionCardCmpController != null) {
			sessionCardCmpController.onNewFriendMessageArrived(jsonNode);
		} else {
			throw new RuntimeException("�Ự������");
		}

		// ֪ͨ�������
		ChattingBoardController chattingBoardController =
				friendChattingBoardDistributorMap.get(sendFromId);
		if (chattingBoardController != null) {
			chattingBoardController.onNewFriendMessageArrived(jsonNode);
		}
	}
}
