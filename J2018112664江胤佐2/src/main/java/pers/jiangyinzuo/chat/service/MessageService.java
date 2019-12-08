package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Message;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface MessageService {
    /**
     * 将message存入数据库
     * @param rawJson 发送给TcpServer的原始字节数组
     */
    void insertMessage(byte[] rawJson);

    /**
     * 查询和好友聊天最近的5条记录
     * @param friendId
     * @return
     */
    List<Message> queryRecentMessage(Long userId, Long friendId);

    List<Message> queryGroupRecentMessage(Long groupId);

    List<String> querySensitiveWords();

    List<String> getSensitiveWords();

    void addSensitiveWord(String word);

    void deleteSensitiveWord(String word);

    void updateSensitiveWord(String newWord, String oldWord);
}
