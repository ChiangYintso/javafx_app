package pers.jiangyinzuo.chat.domain.entity;

import java.util.List;

public class Group implements Session {
	private String groupName;
	private Integer groupId;
	private User founder;
	private String avatarUrl;
	private List<User> manager;
	private List<User> memberList;
	private List<Message> chattingRecord;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public User getFounder() {
		return founder;
	}

	public void setFounder(User founder) {
		this.founder = founder;
	}

	public List<User> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<User> memberList) {
		this.memberList = memberList;
	}

	public List<User> getManager() {
		return manager;
	}

	public void setManager(List<User> manager) {
		this.manager = manager;
	}

	public List<Message> getChattingRecord() {
		return chattingRecord;
	}

	public void setChattingRecord(List<Message> chattingRecord) {
		this.chattingRecord = chattingRecord;
	}

	@Override
	public String getSessionName() {
		return this.groupName;
	}

	@Override
	public String getAvatarUrl() {
		return avatarUrl == null ? DEFAULT_AVATAR_URL : avatarUrl;
	}
	
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
}
