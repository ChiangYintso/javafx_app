package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;

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
    public void updateUserByUserId(Integer userId, User user) {

    }

    @Override
    public void deleteUserByUserId(Integer userId) {

    }
}
