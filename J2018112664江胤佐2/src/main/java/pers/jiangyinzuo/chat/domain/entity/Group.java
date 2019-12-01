package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

/**
 * @author Jiang Yinzuo
 */
public class Group {
	private Long groupId;
	private String groupName;

	private User master;
	private String avatar;

	private GroupRepo groupRepo;

	public Group(Long groupId, String groupName, User master, String avatar) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.master = master;
		this.avatar = avatar;
		this.groupRepo = new GroupRepo();
	}

	public Group() {}

	private Group(Builder builder) {
		setGroupId(builder.groupId);
		setGroupName(builder.groupName);
		setMaster(builder.master);
		setAvatar(builder.avatar);
		this.groupRepo = new GroupRepo();
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getMaster() {
		return master;
	}

	public void setMaster(User master) {
		this.master = master;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public static final class Builder {
		private Long groupId;
		private String groupName;
		private User master;
		private String avatar;

		public Builder() {
		}

		public Builder groupId(Long val) {
			groupId = val;
			return this;
		}

		public Builder groupName(String val) {
			groupName = val;
			return this;
		}

		public Builder master(User val) {
			master = val;
			return this;
		}

		public Builder avatar(String val) {
			avatar = val;
			return this;
		}

		public Group build() {
			return new Group(this);
		}
	}
}
