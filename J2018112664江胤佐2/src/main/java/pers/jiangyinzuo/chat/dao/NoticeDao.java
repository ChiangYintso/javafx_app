package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Notice;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface NoticeDao {
    /**
     * 插入通知
     * @param notice 通知
     */
    void insertNotice(Notice notice);

    /**
     * 根据通知类型和接收者id查询通知
     * @param noticeType 通知类型
     * @param sendToId 接收者id
     */
    List<Notice> queryNotices(String noticeType, Long sendToId);

    List<Notice> queryNoticesBySendToUserId(Long sendToId);

    /**
     * 删除通知
     * @param noticeId
     */
    void deleteNotice(Long noticeId);
}
