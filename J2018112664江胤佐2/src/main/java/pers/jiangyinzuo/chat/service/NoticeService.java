package pers.jiangyinzuo.chat.service;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.domain.entity.Notice;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface NoticeService {
    void insertNotice(byte[] message);

    void insertNotice(JsonNode jsonNode);

    List<Notice> queryNoticeByUserId(Long userId);

    void deleteNotice(Long noticeId);

    int getUnhandledNoticeCount(Long userId);
}
