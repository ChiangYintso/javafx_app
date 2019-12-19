package pers.jiangyinzuo.chat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.dao.FriendDao;
import pers.jiangyinzuo.chat.dao.UserDao;
import pers.jiangyinzuo.chat.dao.mysql.FriendDaoImpl;
import pers.jiangyinzuo.chat.dao.mysql.UserDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.domain.repository.FriendRepo;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;

/**
 * @author Jiang Yinzuo
 */
public class FriendServiceImpl implements FriendService {

    // DAO����
    private UserDao userDao = new UserDaoImpl();
    private FriendDao friendDao = new FriendDaoImpl();

    /**
     * ��Ӻ���
     * @param friendId ����ID
     */
    @Override
    public void addFriend(Long friendId) {
        friendDao.addFriend(UserState.getSingleton().getUser().getUserId(), friendId);
    }

    /**
     * ɾ������
     * @param friendId ����ID
     */
    @Override
    public void deleteFriend(Long friendId) {
        friendDao.deleteFriendship(UserState.getSingleton().getUser().getUserId(), friendId);
    }

    /**
     * ͨ��userId�����û�
     * @param userId �û�ID
     */
    @Override
    public User searchUser(Long userId) {
        return userDao.queryUserByUserId(userId);
    }

    /**
     * ��ʼ�������б�ʱ��ѯ�ʺ��ѵ�����״̬
     * @param userId �û�ID
     */
    @Override
    public synchronized void requestForFriendsStatus(Long userId) {
        // ��ȡ�����б�
        List<Long> friendIdList = new FriendRepo().getFriendIdList(userId);
        // ����JSON��ʽ��Ϣ
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> rawJson = new HashMap<>(10);
        rawJson.put("option", JsonHelper.Option.ASK_FOR_FRIEND_IF_IS_ONLINE);
        rawJson.put("friendIdList", friendIdList);
        rawJson.put("sendFrom", userId);

        // ��ȡTcp�ͻ��˵�����������Ϣ��Tcp������
        try {
            Main.getTcpClient().sendMessage(objectMapper.writeValueAsBytes(rawJson));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���º��ѷ���
     * @param friendId ����ID
     * @param friendCategory �µĺ��ѷ�������
     */
    @Override
    public void updateFriendCategory(Long friendId, String friendCategory) {
        friendDao.updateFriendCategory(UserState.getSingleton().getUser().getUserId(), friendId, friendCategory);
    }
}
