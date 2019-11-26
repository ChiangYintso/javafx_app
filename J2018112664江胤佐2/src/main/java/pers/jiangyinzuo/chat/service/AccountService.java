package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.common.CustomInfo;

public interface AccountService {
	
	CustomInfo register(String username, String password);
	
	CustomInfo login(String username, String password);
}
