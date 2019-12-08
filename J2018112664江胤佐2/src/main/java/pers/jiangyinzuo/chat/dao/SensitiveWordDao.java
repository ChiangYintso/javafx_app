package pers.jiangyinzuo.chat.dao;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface SensitiveWordDao {
    List<String> queryAllSensitiveWord();

    void addSensitiveWord(String word);

    void deleteSensitiveWord(String word);

    void updateSensitiveWord(String newWord, String oldWord);
}
