package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.FriendDao;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FriendDaoImpl implements FriendDao {
    @Override
    public Map<String, List<User>> queryFriendCategoryMapByUserId(Integer userId) {
        return null;
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {

    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        String sql = "INSERT INTO chat_friendship(user_id, friend_id) VALUES (?, ?)";
        List<List<Object>> parameters = new ArrayList<>();
        List<Object> list1 = new ArrayList<>();
        list1.add(userId);
        list1.add(friendId);
        List<Object> list2 = new ArrayList<>();
        list2.add(friendId);
        list2.add(userId);
        parameters.add(list1);
        parameters.add(list2);
        try {
            MySqlHelper.bulkExecuteUpdate(sql, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
