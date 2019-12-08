package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Message;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface MessageService {
    /**
     * ��message�������ݿ�
     * @param rawJson ���͸�TcpServer��ԭʼ�ֽ�����
     */
    void insertMessage(byte[] rawJson);

    /**
     * ��ѯ�ͺ������������5����¼
     * @param friendId
     * @return
     */
    List<Message> queryRecentMessage(Long userId, Long friendId);

    List<Message> queryGroupRecentMessage(Long groupId);

    List<Message> queryGroupMessage(Long groupId, int start, int offset, Timestamp limitTime);

    List<Message> queryUserMessageInGroup(Long userId, Long groupId, int start, int offset, Timestamp limitTime);

    List<Message> queryUserMessageToAllFriends(Long userId, int start, int offset, Timestamp limitTime);

    List<Message> queryUserMessageToFriends(Long userId, Long friendId, int start, int offset, Timestamp limitTime);

    List<String> querySensitiveWords();

    List<String> getSensitiveWords();

    void addSensitiveWord(String word);

    void deleteSensitiveWord(String word);

    void updateSensitiveWord(String newWord, String oldWord);
}
