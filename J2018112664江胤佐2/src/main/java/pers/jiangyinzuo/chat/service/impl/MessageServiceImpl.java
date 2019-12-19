package pers.jiangyinzuo.chat.service.impl;

import pers.jiangyinzuo.chat.client.state.SensitiveWordsState;
import pers.jiangyinzuo.chat.dao.MessageDao;
import pers.jiangyinzuo.chat.dao.SensitiveWordDao;
import pers.jiangyinzuo.chat.dao.mysql.MessageDaoImpl;
import pers.jiangyinzuo.chat.dao.mysql.SensitiveWordDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.service.MessageService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao = new MessageDaoImpl();
    private SensitiveWordDao sensitiveWordDao = new SensitiveWordDaoImpl();
    private List<String> sensitiveWords = new ArrayList<>();

    /**
     * 将message存入数据库
     *
     * @param rawJson 发送给TcpServer的原始字节数组
     */
    @Override
    public void insertMessage(byte[] rawJson) {
        messageDao.insertMessage(Message.parseToMessageEntity(rawJson));
    }

    /**
     * 查询和好友聊天最近的5条记录
     *
     * @param friendId 好友id
     * @return 聊天记录列表
     */
    @Override
    public List<Message> queryRecentMessage(Long userId, Long friendId) {
        return messageDao.queryMessagesByUserId(userId, friendId, 0, 5);
    }

    @Override
    public List<Message> queryGroupRecentMessage(Long groupId) {
        return messageDao.queryMessagesBySendToId(groupId, 10, 0, 5);
    }

    @Override
    public List<Message> queryGroupMessage(Long groupId, int start, int offset, Timestamp limitTime) {
        return messageDao.queryMessagesBySendToId(groupId, 10, start, offset, limitTime);
    }

    @Override
    public List<Message> queryUserMessageInGroup(Long userId, Long groupId, int start, int offset, Timestamp limitTime) {
        return messageDao.queryMessagesByUserIdAndGroupId(userId, groupId, start, offset, limitTime);
    }

    @Override
    public List<Message> queryUserMessageToAllFriends(Long userId, int start, int offset, Timestamp limitTime) {
        return messageDao.queryMessagesBySendToId(userId, 1, start, offset, limitTime);
    }

    @Override
    public List<Message> queryUserMessageToFriends(Long userId, Long friendId, int start, int offset, Timestamp limitTime) {
        return messageDao.queryMessagesByUserId(userId, friendId, start, offset, limitTime);
    }

    @Override
    public List<String> querySensitiveWords() {
        sensitiveWords = sensitiveWordDao.queryAllSensitiveWord();
        return sensitiveWords;
    }

    @Override
    public List<String> getSensitiveWords() {
        if (sensitiveWords.size() == 0) {
            return querySensitiveWords();
        }
        return sensitiveWords;
    }

    @Override
    public void addSensitiveWord(String word) {
        sensitiveWordDao.addSensitiveWord(word);
        sensitiveWords.add(word);
    }

    @Override
    public void deleteSensitiveWord(String word) {
        sensitiveWordDao.deleteSensitiveWord(word);
        sensitiveWords.remove(word);
    }

    @Override
    public void updateSensitiveWord(String newWord, String oldWord) {
        sensitiveWordDao.updateSensitiveWord(newWord, oldWord);
        Collections.replaceAll(sensitiveWords, oldWord, newWord);
    }
}
