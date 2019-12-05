package pers.jiangyinzuo.chat.dao;

import pers.jiangyinzuo.chat.domain.entity.Notice;

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
    void queryNotices(Integer noticeType, Long sendToId);
}
