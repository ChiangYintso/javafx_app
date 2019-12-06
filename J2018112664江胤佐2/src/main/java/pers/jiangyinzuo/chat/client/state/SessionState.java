package pers.jiangyinzuo.chat.client.state;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �Ự״̬��
 * @author Jiang Yinzuo
 *
 */
public class SessionState {

	public interface Distributor {
		/**
		 * �µĺ�����Ϣ����
		 * @param jsonNode ������Ϣ
		 */
		void onNewFriendMessageArrived(JsonNode jsonNode);

		/**
		 * ע���Ϊ������
		 */
		void registerDistribution();
	}

	private static SessionCardCmpController.Session selectedSession;

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

	private static Map<Long, SessionCardCmpController> friendSessionCardCmpDistributorMap = new ConcurrentHashMap<>(20);

	private static Map<Long ,SessionCardCmpController> groupSessionCardCmpDistributorMap = new ConcurrentHashMap<>(20);

	/**
	 * TcpClient�յ���Ϣ��֪ͨ��Ӧ�ĺ��ѻỰ
	 * @param jsonNode ��Ϣ
	 */
	public static void notifyFriendSession(JsonNode jsonNode) {
		Long sendFromId = JsonHelper.getSendFromId(jsonNode).longValue();
		SessionCardCmpController sessionCardCmpController =
				friendSessionCardCmpDistributorMap.get(sendFromId);
		if (sessionCardCmpController != null) {
			sessionCardCmpController.onNewFriendMessageArrived(jsonNode);
		} else {
			throw new RuntimeException("�Ự������");
		}
	}
}
