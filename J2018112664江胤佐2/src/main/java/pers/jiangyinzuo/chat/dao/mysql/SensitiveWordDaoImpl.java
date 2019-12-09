package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.SensitiveWordDao;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class SensitiveWordDaoImpl implements SensitiveWordDao {
    @Override
    public List<String> queryAllSensitiveWord() {
        String sql = "SELECT * FROM chat_sensitive_word_dict";
        try {
            return MySqlHelper.queryMany(String.class, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void addSensitiveWord(String word) {
        String sql = "INSERT INTO chat_sensitive_word_dict(sensitive_word) VALUES (?)";
        MySqlHelper.executeUpdate(sql, word);
    }

    @Override
    public void deleteSensitiveWord(String word) {
        String sql = "DELETE FROM chat_sensitive_word_dict WHERE sensitive_word = ?";
        MySqlHelper.executeUpdate(sql, word);
    }

    @Override
    public void updateSensitiveWord(String newWord, String oldWord) {
        String sql = "UPDATE chat_sensitive_word_dict SET sensitive_word = ? WHERE sensitive_word = ?";
        MySqlHelper.executeUpdate(sql, newWord, oldWord);
    }
}
