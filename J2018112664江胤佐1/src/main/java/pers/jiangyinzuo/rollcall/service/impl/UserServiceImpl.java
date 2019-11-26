package pers.jiangyinzuo.rollcall.service.impl;

import pers.jiangyinzuo.rollcall.service.UserService;

public class UserServiceImpl implements UserService {

	private static UserServiceImpl userServiceImpl = null;
	
	private Integer id;
	private String pwd;
	private Short isStudent;
	
	private UserServiceImpl() {
		
	}

	public static UserServiceImpl getSingleton() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getPwd() {
		return pwd;
	}

	@Override
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public Short getIsStudent() {
		return isStudent;
	}

	@Override
	public void setIsStudent(Short isStudent) {
		this.isStudent = isStudent;
	}

}
