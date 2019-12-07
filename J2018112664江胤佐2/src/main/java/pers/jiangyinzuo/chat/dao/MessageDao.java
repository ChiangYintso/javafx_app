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
     * @param user1Id �û�1id
     * @param user2Id �û�2id
     * @return
     */
    List<Message> queryMessagesByUserId(Long user1Id, Long user2Id, Integer row, Integer offset);

    /**
     * ����sendToId��messageType��ѯMessage
     *
     * @param sendToId
     * @param messageType > 10: ��ѯȺ����Ϣ; < 10: ��ѯ����������Ϣ
     * @param row
     * @param offset      ƫ����
     * @return
     */
    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset);

    void insertMessage(Message message);

    void deleteMessage(Message message);
}
