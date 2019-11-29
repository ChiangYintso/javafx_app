package pers.jiangyinzuo.chat.service.impl;

import java.util.ArrayList;

import pers.jiangyinzuo.chat.common.CustomInfo;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Override
	public CustomInfo register(String username, String password) {
		return new CustomInfo((short) 200, "注册成功", new User(123, "Cindy", "Hello JavaFX",null, new ArrayList<>()));
	}

	@Override
	public CustomInfo login(String username, String password) {
		return new CustomInfo((short) 200, "登录成功", new User(123, "Cindy", "Hello JavaFX",null, new ArrayList<>()));
	}

}
