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
     * 返回最近的count条聊天记录
     *
     * @param sendFromId 用户1id
     * @param sendToId 用户2id
     * @return
     */
    List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset);

    List<Message> queryMessagesByUserIdAndGroupId(Long userId, Long groupId, Integer row, Integer offset, Timestamp timestamp);

    List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset, Timestamp timestamp);

    /**
     * 根据sendToId和messageType查询Message
     *
     * @param sendToId
     * @param messageType > 10: 查询群聊消息; < 10: 查询好友聊天消息
     * @param row
     * @param offset      偏移量
     * @return
     */
    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset, Timestamp limitTime);

    List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset);

    void insertMessage(Message message);
}
