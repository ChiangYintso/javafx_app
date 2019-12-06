package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Notice;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface NoticeDao {
    /**
     * ����֪ͨ
     * @param notice ֪ͨ
     */
    void insertNotice(Notice notice);

    /**
     * ����֪ͨ���ͺͽ�����id��ѯ֪ͨ
     * @param noticeType ֪ͨ����
     * @param sendToId ������id
     */
    List<Notice> queryNotices(String noticeType, Long sendToId);

    List<Notice> queryNoticesBySendToUserId(Long sendToId);

    /**
     * ɾ��֪ͨ
     * @param noticeId
     */
    void deleteNotice(Long noticeId);
}
