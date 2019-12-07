package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.MessageDao;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
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
     * @param user1Id 好友id
     * @return
     */
    @Override
    public List<Message> queryMessagesByUserId(Long user1Id, Long user2Id, Integer row, Integer offset) {
        String sql = "SELECT * FROM chat_message WHERE (send_from = ? AND send_to = ?) OR (send_from = ? AND send_to = ?) ORDER BY send_time DESC LIMIT ?, ?";
        try {
            return MySqlHelper.queryMany(Message.class, sql, user1Id, user2Id, user2Id, user1Id, row, offset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Message> queryMessagesBySendToId(Long sendToId, Integer messageType, Integer row, Integer offset) {
        String sql = "SELECT * FROM chat_message WHERE send_to = ? AND message_type > ? ORDER BY send_time DESC LIMIT ?, ?";
        try {
            return MySqlHelper.queryMany(Message.class, sql, sendToId, messageType, row, offset);
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
