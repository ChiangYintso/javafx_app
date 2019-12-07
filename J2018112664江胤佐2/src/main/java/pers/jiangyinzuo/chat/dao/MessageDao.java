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
     * @param user1Id 用户1id
     * @param user2Id 用户2id
     * @return
     */
    List<Message> queryMessagesByUserId(Long user1Id, Long user2Id, Integer row, Integer offset);

    List<Message> queryMessagesByGroupId(Instant fromTime, Integer groupId);

    void insertMessage(Message message);

    void deleteMessage(Message message);
}
