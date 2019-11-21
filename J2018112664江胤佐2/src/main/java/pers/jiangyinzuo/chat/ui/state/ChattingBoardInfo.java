package main.java.pers.jiangyinzuo.chat.ui.state;

import main.java.pers.jiangyinzuo.chat.entity.Group;
import main.java.pers.jiangyinzuo.chat.entity.Session;
import main.java.pers.jiangyinzuo.chat.entity.User;

/**
 * 临时存放打开ChattingBoard的相关信息，便于初始化
 * @author Jiang Yinzuo
 *
 */
public class ChattingBoardInfo {
	private static Session session;
	
	private static User friend;
	
	private static Group group;

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		ChattingBoardInfo.session = session;
		if (session instanceof User) {
			ChattingBoardInfo.friend = (User) session;
		} else if (session instanceof Group) {
			ChattingBoardInfo.group = (Group) session;
		}
	}

	public static User getFriend() {
		return friend;
	}

	public static void setFriend(User user) {
		ChattingBoardInfo.friend = user;
	}

	public static Group getGroup() {
		return group;
	}

	public static void setGroup(Group group) {
		ChattingBoardInfo.group = group;
	}
}
