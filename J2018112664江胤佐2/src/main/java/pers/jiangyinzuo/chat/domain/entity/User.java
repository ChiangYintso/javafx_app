package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.repository.FriendRepo;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

/**
 * @author Jiang Yinzuo
 */
public class User {
	private Long userId;
	private String userName;
	private String password;
	private String intro;
	private String avatar;

	private GroupRepo groupRepo;
	private FriendRepo friendRepo;

	public User(Long userId, String userName, String password, String intro, String avatar) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.intro = intro;
		this.avatar = avatar;
		this.groupRepo = new GroupRepo();
		this.friendRepo = new FriendRepo();
	}

	public User() {}

	private User(Builder builder) {
		setUserId(builder.userId);
		setUserName(builder.userName);
		setPassword(builder.password);
		setIntro(builder.intro);
		setAvatar(builder.avatar);
		this.groupRepo = new GroupRepo();
		this.friendRepo = new FriendRepo();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public static final class Builder {
		private Long userId;
		private String userName;
		private String password;
		private String intro;
		private String avatar;

		public Builder() {
		}

		public Builder userId(Long val) {
			userId = val;
			return this;
		}

		public Builder userName(String val) {
			userName = val;
			return this;
		}

		public Builder password(String val) {
			password = val;
			return this;
		}

		public Builder intro(String val) {
			intro = val;
			return this;
		}

		public Builder avatar(String val) {
			avatar = val;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
