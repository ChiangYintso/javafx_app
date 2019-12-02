package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.MessageDao;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> queryMessagesByRoomId(Instant fromTime, Integer roomId) {
        return null;
    }

    @Override
    public List<Message> queryMessagesByGroupId(Instant fromTime, Integer groupId) {
        return null;
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
