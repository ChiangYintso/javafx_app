package pers.jiangyinzuo.chat.client.state;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话状态类
 * @author Jiang Yinzuo
 *
 */
public class SessionState {

	public interface Distributor {
		/**
		 * 新的好友消息到来
		 * @param jsonNode 好友消息
		 */
		void onNewFriendMessageArrived(JsonNode jsonNode);

		/**
		 * 注册成为订阅者
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
	 * TcpClient收到消息后通知相应的好友会话
	 * @param jsonNode 消息
	 */
	public static void notifyFriendSession(JsonNode jsonNode) {
		Long sendFromId = JsonHelper.getSendFromId(jsonNode).longValue();
		SessionCardCmpController sessionCardCmpController =
				friendSessionCardCmpDistributorMap.get(sendFromId);
		if (sessionCardCmpController != null) {
			sessionCardCmpController.onNewFriendMessageArrived(jsonNode);
		} else {
			throw new RuntimeException("会话不存在");
		}
	}
}
