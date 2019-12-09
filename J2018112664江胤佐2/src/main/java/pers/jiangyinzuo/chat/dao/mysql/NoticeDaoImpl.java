package pers.jiangyinzuo.chat.dao.mysql;

import pers.jiangyinzuo.chat.dao.NoticeDao;
import pers.jiangyinzuo.chat.domain.entity.Notice;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.helper.MySqlHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class NoticeDaoImpl implements NoticeDao {
    @Override
    public void insertNotice(Notice notice) {
        String sql = "INSERT INTO chat_notice(NOTICE_TYPE, NOTICE_DATA, SEND_TO_ID)" +
                " VALUES(?, ?, ?)";
        try {
            MySqlHelper.executeUpdate(sql, notice.getNoticeType(), notice.getNoticeData(), notice.getSendToId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notice> queryNotices(String noticeType, Long sendToId) {
        String sql = "SELECT * FROM chat_notice WHERE send_to_id = ? AND notice_type = ?";
        try {
            return MySqlHelper.queryMany(Notice.class, sql, sendToId, noticeType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Notice> queryNoticesBySendToUserId(Long sendToId) {
        String sql = "SELECT * FROM chat_notice WHERE send_to_id = ? AND notice_type <> '" +
                JsonHelper.Option.GROUP_MESSAGE + "'";
        try {
            return MySqlHelper.queryMany(Notice.class, sql, sendToId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 查询用户未处理的消息数量
     *
     * @param userId
     * @return
     */
    @Override
    public int queryUnhandledNoticeCount(Long userId) {
        String sql = "SELECT COUNT(*) FROM chat_notice WHERE send_to_id = ?";
        return MySqlHelper.executeQueryCount(sql, userId);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        String sql = "DELETE FROM chat_notice WHERE notice_id = ?";
        try {
            MySqlHelper.executeUpdate(sql, noticeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
