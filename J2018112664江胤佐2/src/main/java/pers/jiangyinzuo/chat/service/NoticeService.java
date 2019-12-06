package pers.jiangyinzuo.chat.service;

import pers.jiangyinzuo.chat.domain.entity.Notice;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface NoticeService {
    void insertNotice(byte[] message);

    List<Notice> queryNoticeByUserId(Long userId);

    void deleteNotice(Long noticeId);
}
