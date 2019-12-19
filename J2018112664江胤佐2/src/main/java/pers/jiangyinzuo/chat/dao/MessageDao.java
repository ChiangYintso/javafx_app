package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Message;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface MessageDao {
    /**
     * ���������count�������¼
     *
     * @param sendFromId �û�1id
     * @param sendToId �û�2id
     * @return
     */
    List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset);

    List<Message> queryMessagesByUserIdAndGroupId(Long userId, Long groupId, Integer row, Integer offset, Timestamp timestamp);

    List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset, Timestamp timestamp);

    /**
     * ����sendToId��messageType��ѯMessage
     *
     * @param sendToId
     * @param messageType > 10: ��ѯȺ����Ϣ; < 10: ��ѯ����������Ϣ
     * @param row
     * @param offset      ƫ����
     * @return
     */
    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset, Timestamp limitTime);

    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset);

    void insertMessage(Message message);
}
