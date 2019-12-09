package pers.jiangyinzuo.chat.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.dao.NoticeDao;
import pers.jiangyinzuo.chat.dao.mysql.NoticeDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.Notice;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.NoticeService;

import java.io.IOException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class NoticeServiceImpl implements NoticeService {

    private NoticeDao noticeDao = new NoticeDaoImpl();

    /**
     * {
     *     "option":
     *     "data":
     *     "sendToId":
     * }
     * @param message
     */
    @Override
    public void insertNotice(byte[] message) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
           jsonNode = objectMapper.readTree(message);
           String noticeType = jsonNode.get("option").asText();
           String noticeData = jsonNode.get("data").toString();
           Long sendToId = JsonHelper.getSendToId(message).longValue();
           noticeDao.insertNotice(new Notice.Builder()
                   .noticeType(noticeType)
                   .noticeData(noticeData)
                   .sendToId(sendToId)
                   .build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertNotice(JsonNode jsonNode) {
        String noticeType = jsonNode.get("option").asText();
        String noticeData = jsonNode.get("data").toString();
        long sendToId = jsonNode.get("data").get("sendTo").asInt();
        noticeDao.insertNotice(new Notice.Builder()
                .noticeType(noticeType)
                .noticeData(noticeData)
                .sendToId(sendToId)
                .build());
    }

    @Override
    public List<Notice> queryNoticeByUserId(Long userId) {
        return noticeDao.queryNoticesBySendToUserId(userId);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        noticeDao.deleteNotice(noticeId);
    }

    @Override
    public int getUnhandledNoticeCount(Long userId) {
        return noticeDao.queryUnhandledNoticeCount(userId);
    }


}
