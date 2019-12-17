package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUsers();
}
