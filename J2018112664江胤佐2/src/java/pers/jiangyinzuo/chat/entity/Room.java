package java.pers.jiangyinzuo.chat.entity;

import java.util.List;

public class Room {
	private Integer roomId;
	private List<User> userList;
	private List<String> chattingRecords;

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<String> getChattingRecords() {
		return chattingRecords;
	}

	public void setChattingRecords(List<String> chattingRecords) {
		this.chattingRecords = chattingRecords;
	}
}
