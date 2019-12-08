package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.MessageDao;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class MessageDaoImpl implements MessageDao {

    /**
     * 返回最近的count条聊天记录
     *
     * @param sendFromId 好友id
     * @return
     */
    @Override
    public List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset) {
        String sql = "SELECT * FROM chat_message WHERE (send_from = ? AND send_to = ?) OR (send_from = ? AND send_to = ?) ORDER BY send_time DESC LIMIT ?, ?";
        try {
            return MySqlHelper.queryMany(Message.class, sql, sendFromId, sendToId, sendToId, sendFromId, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Message> queryMessagesByUserIdAndGroupId(Long userId, Long groupId, Integer row, Integer offset, Timestamp timestamp) {
        String sql = "SELECT * FROM chat_message WHERE (send_from = ? AND send_to = ?) AND send_time < ? ORDER BY send_time DESC LIMIT ?, ?";
        try {
            return MySqlHelper.queryMany(Message.class, sql, userId, groupId, timestamp, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public List<Message> queryMessagesByUserId(Long sendFromId, Long sendToId, Integer row, Integer offset, Timestamp timestamp) {
        String sql = "SELECT * FROM chat_message WHERE (chat_message.send_time < ?) AND (send_from = ? AND send_to = ?) OR (send_from = ? AND send_to = ?) ORDER BY send_time DESC LIMIT ?, ?";
        try {
            return MySqlHelper.queryMany(Message.class, sql, timestamp, sendFromId, sendToId, sendToId, sendFromId, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据sendToId和messageType查询Message
     *
     * @param sendToId
     * @param messageType > 10: 查询群聊消息; < 10: 查询好友聊天消息
     * @param row
     * @param offset      偏移量
     * @param limitTime
     * @return
     */
    @Override
    public List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset, Timestamp limitTime) {
        String sql;
        if (messageType >= 9) {
            sql = "SELECT * FROM chat_message WHERE send_to = ? AND message_type > 9 AND send_time < ? ORDER BY send_time DESC LIMIT ?, ?";
        } else {
            sql = "SELECT * FROM chat_message WHERE send_to = ? AND message_type < 10 AND send_time < ? ORDER BY send_time DESC LIMIT ?, ?";
        }

        try {
            return MySqlHelper.queryMany(Message.class, sql, sendToId, limitTime, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset) {
        String sql;
        if (messageType >= 9) {
            sql = "SELECT * FROM chat_message WHERE send_to = ? AND message_type > 9 ORDER BY send_time DESC LIMIT ?, ?";
        } else {
            sql = "SELECT * FROM chat_message WHERE send_to = ? AND message_type < 10 ORDER BY send_time DESC LIMIT ?, ?";
        }
        try {
            return MySqlHelper.queryMany(Message.class, sql, sendToId, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void insertMessage(Message message) {
        String sql = "INSERT INTO chat_message(message_type, message_content," +
                "send_from, send_to, send_time)" +
                " VALUES(?, ?, ?, ?, NOW())";
        try {
            MySqlHelper.executeUpdate(sql,
                    message.getMessageType(),
                    message.getMessageContent(),
                    message.getSendFrom(),
                    message.getSendTo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(Message message) {

    }
}
