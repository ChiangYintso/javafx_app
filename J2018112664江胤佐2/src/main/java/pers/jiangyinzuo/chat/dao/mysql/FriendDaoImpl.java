package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.FriendDao;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class FriendDaoImpl implements FriendDao {
    @Override
    public Map<String, List<User>> queryFriendCategoryMapByUserId(Integer userId) {
        return null;
    }

    @Override
    public void deleteFriendship(Long userId, Long friendId) {
        String sql = "DELETE FROM chat_friendship WHERE user_id = ? AND friend_id = ? OR user_id = ? AND friend_id = ?";
        MySqlHelper.executeUpdate(sql, userId, friendId, friendId, userId);
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

    /**
     * 更新好友分组
     *
     * @param friendId
     * @param friendCategory
     */
    @Override
    public void updateFriendCategory(Long userId, Long friendId, String friendCategory) {
        String sql = "UPDATE chat_friendship SET friend_category = ? WHERE user_id = ? AND friend_id = ?";
        MySqlHelper.executeUpdate(sql, friendCategory, userId, friendId);
    }
}
