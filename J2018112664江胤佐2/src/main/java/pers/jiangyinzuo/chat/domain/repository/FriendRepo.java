package pers.jiangyinzuo.chat.domain.repository;

import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class FriendRepo {

    public List<User> getFriendList(Long userId) {
        String sql = "SELECT chat_user.*, chat_friendship.friend_category FROM chat_user, chat_friendship" +
                " WHERE chat_friendship.user_id = ? AND chat_friendship.friend_id = chat_user.user_id";
        return MySqlHelper.queryMany(User.class, sql, userId);
    }

    public List<Long> getFriendIdList(Long userId) {
        String sql = "SELECT chat_friendship.friend_id FROM chat_friendship WHERE  user_id = ?";
        return MySqlHelper.queryMany(Long.class, sql, userId);
    }
}
