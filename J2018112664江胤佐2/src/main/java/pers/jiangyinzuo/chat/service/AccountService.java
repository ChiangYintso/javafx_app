package main.java.pers.jiangyinzuo.chat.service;

import main.java.pers.jiangyinzuo.chat.common.CustomInfo;

public interface AccountService {
	
	CustomInfo register(String username, String password);
	
	CustomInfo login(String username, String password);
}
