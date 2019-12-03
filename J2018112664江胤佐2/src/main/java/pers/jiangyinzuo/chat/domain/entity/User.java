package pers.jiangyinzuo.chat.domain.entity;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;
import pers.jiangyinzuo.chat.domain.repository.FriendRepo;
import pers.jiangyinzuo.chat.domain.repository.GroupRepo;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Jiang Yinzuo
 */
@TableMapper("chat_user")
public class User {
	private static String DEFAULT_AVATAR_URL;

	@FieldMapper(name = "user_id")
	private Long userId;

	@FieldMapper(name = "user_name")
	private String userName;

	@FieldMapper(name = "password")
	private String password;

	@FieldMapper(name = "intro")
	private String intro;

	@FieldMapper(name = "user_avatar")
	private String avatar;

    /**
	 * 作为好友时的好友分组
	 */
    @FieldMapper(name = "chat_friendship.friend_category")
	private String friendCategory;

	private GroupRepo groupRepo;
	private FriendRepo friendRepo;

	public User(Long userId, String userName, String password, String intro, String avatar) {
		this();
	    this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.intro = intro;
		this.avatar = avatar;
	}

	public User() {
		DEFAULT_AVATAR_URL = "file:" + URLDecoder.decode(User.class.getClassLoader().getResource("avatar.png").getPath(), StandardCharsets.UTF_8);
		this.groupRepo = new GroupRepo();
        this.friendRepo = new FriendRepo();
    }

    private User(Builder builder) {
	    this();
		setUserId(builder.userId);
		setUserName(builder.userName);
		setPassword(builder.password);
		setIntro(builder.intro);
		setAvatar(builder.avatar);
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

	public String getFriendCategory() {
        return friendCategory;
    }

    public void setFriendCategory(String friendCategory) {
        this.friendCategory = friendCategory;
    }

	public String getAvatar() {
		return "".equals(avatar) || avatar == null ? DEFAULT_AVATAR_URL : avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = "".equals(avatar) || avatar == null ? DEFAULT_AVATAR_URL : avatar;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
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

	public static void main(String[] args) {
		User user = new User.Builder().build();
		try {
			System.out.println(URLDecoder.decode(user.getAvatar(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
