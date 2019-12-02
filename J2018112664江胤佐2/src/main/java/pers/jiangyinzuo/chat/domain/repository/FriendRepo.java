package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.domain.entity.User;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class FriendRepo {
    public List<User> getFriendList(Long userId) {
        String sql = "SELECT chat_user.*, ";
    }
}
