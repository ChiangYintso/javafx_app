package pers.jiangyinzuo.chat.service.impl;

import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.dao.mysql.UserDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> queryAllUsers() {
        return userDao.queryAllUsers();
    }
}
