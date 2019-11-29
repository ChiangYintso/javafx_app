package pers.jiangyinzuo.chat.domain.entity;

import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class User implements Session {
	private Integer userId;
	private String userName;
	private String pwd;
	private String intro;
	private String avatarUrl;
	private List<User> friendList;
	private List<Group> groupList;
	private List<Group> priviledgeGroupList;
	private Map<String, List<User>> friendCategoryMap;

	@Override
	public String getAvatarUrl() {
		return avatarUrl == null ? DEFAULT_AVATAR_URL : avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Boolean isOnLine() {
		return isOnLine;
	}

	public void setOnLineStatus(Boolean isOnLine) {
		this.isOnLine = isOnLine;
	}

	private Boolean isOnLine;

	public User(Integer userId, String userName, String intro, String avatarUrl, List<Group> groupList) {
		this.userId = userId;
		this.userName = userName;
		this.intro = intro;
		this.groupList = groupList;
		if (avatarUrl == null) {
			this.avatarUrl = DEFAULT_AVATAR_URL;
		} else {
			this.avatarUrl = avatarUrl;
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<Group> getPriviledgeGroupList() {
		return priviledgeGroupList;
	}

	public void setPriviledgeGroupList(List<Group> priviledgeGroupList) {
		this.priviledgeGroupList = priviledgeGroupList;
	}

	public Map<String, List<User>> getFriendCategoryMap() {
		return friendCategoryMap;
	}

	public void setFriendCategoryMap(Map<String, List<User>> friendCategoryMap) {
		this.friendCategoryMap = friendCategoryMap;
	}

	@Override
	public String getSessionName() {
		return this.userName;
	}
}
