package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Message;

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

    List<String> querySensitiveWords();

    List<String> getSensitiveWords();

    void addSensitiveWord(String word);

    void deleteSensitiveWord(String word);

    void updateSensitiveWord(String newWord, String oldWord);
}
