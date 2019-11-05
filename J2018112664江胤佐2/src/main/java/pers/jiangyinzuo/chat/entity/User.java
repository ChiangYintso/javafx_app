package main.java.pers.jiangyinzuo.chat.entity;

import java.util.List;
import java.util.Map;

public class User {
	private Integer userId;
	private String userName;
	private String pwd;
	private String intro;
	private List<User> friendList;
	private List<Group> groupList;
	private List<Group> priviledgeGroupList;
	private Map<String, List<User>> friendCategoryMap;
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
}
