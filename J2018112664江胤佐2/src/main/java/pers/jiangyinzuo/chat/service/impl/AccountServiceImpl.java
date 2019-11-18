package main.java.pers.jiangyinzuo.chat.service.impl;

import main.java.pers.jiangyinzuo.chat.common.CustomInfo;
import main.java.pers.jiangyinzuo.chat.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Override
	public CustomInfo register(String username, String password) {
		return new CustomInfo((short) 200, "注册成功");
	}

	@Override
	public CustomInfo login(String username, String password) {
		return new CustomInfo((short) 200, "登录成功");
	}

}
