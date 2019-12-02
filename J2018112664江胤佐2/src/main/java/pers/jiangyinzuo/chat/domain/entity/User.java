package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;
import pers.jiangyinzuo.chat.domain.repository.FriendRepo;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_user")
public class User {
	private static final String DEFAULT_AVATAR_URL = "file:pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png";

	@FieldMapper(name = "user_id")
	private Long userId;

	@FieldMapper(name = "user_name")
	private String userName;

	@FieldMapper(name = "password")
	private String password;

	@FieldMapper(name = "intro")
	private String intro;

	@FieldMapper(name = "user_avatar")
	private String avatar = DEFAULT_AVATAR_URL;

	/**
	 * 作为好友时的好友分组
	 */
	private String friendCategory = "默认分组";
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

	public List<User> getFriendList() {
		return friendRepo.getFriendList(userId);
	}

	public List<Group> getGroupList() {
		return groupRepo.getGroupListByUserId(userId);
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
		private String avatar = DEFAULT_AVATAR_URL;

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
