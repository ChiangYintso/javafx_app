package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User queryUserByUserId(Long userId) {
        String sql = "SELECT * FROM chat_user WHERE user_id = ?";
        return MySqlHelper.queryOne(User.class, sql, userId);
    }

    @Override
    public Long insertUser(User user) {
        String sql = "INSERT INTO chat_user(user_name, " +
                "password) VALUES (?, ?)";
        Long primaryKey;
        try {
            primaryKey = MySqlHelper.executeUpdateReturnPrimaryKey(sql, user.getUserName(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
            return -1L;
        }
        return primaryKey;
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE chat_user SET user_name = ?, user_avatar = ?, password = ?, intro = ?, is_blocked = ? WHERE user_id = ?";
        MySqlHelper.executeUpdate(sql, user.getUserName(), user.getAvatar(), user.getPassword(), user.getIntro(), user.isBlocked(), user.getUserId());
    }

    @Override
    public void deleteUserByUserId(Integer userId) {

    }

    @Override
    public List<User> queryAllUsers() {
        String sql = "SELECT * FROM chat_user";
        return MySqlHelper.queryMany(User.class, sql);
    }
}
