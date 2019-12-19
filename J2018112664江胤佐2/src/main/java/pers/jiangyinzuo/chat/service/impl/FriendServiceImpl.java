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

    // DAO对象
    private UserDao userDao = new UserDaoImpl();
    private FriendDao friendDao = new FriendDaoImpl();

    /**
     * 添加好友
     * @param friendId 好友ID
     */
    @Override
    public void addFriend(Long friendId) {
        friendDao.addFriend(UserState.getSingleton().getUser().getUserId(), friendId);
    }

    /**
     * 删除好友
     * @param friendId 好友ID
     */
    @Override
    public void deleteFriend(Long friendId) {
        friendDao.deleteFriendship(UserState.getSingleton().getUser().getUserId(), friendId);
    }

    /**
     * 通过userId查找用户
     * @param userId 用户ID
     */
    @Override
    public User searchUser(Long userId) {
        return userDao.queryUserByUserId(userId);
    }

    /**
     * 初始化好友列表时，询问好友的上线状态
     * @param userId 用户ID
     */
    @Override
    public synchronized void requestForFriendsStatus(Long userId) {
        // 获取好友列表
        List<Long> friendIdList = new FriendRepo().getFriendIdList(userId);
        // 生成JSON格式消息
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> rawJson = new HashMap<>(10);
        rawJson.put("option", JsonHelper.Option.ASK_FOR_FRIEND_IF_IS_ONLINE);
        rawJson.put("friendIdList", friendIdList);
        rawJson.put("sendFrom", userId);

        // 获取Tcp客户端单例，发送消息至Tcp服务器
        try {
            Main.getTcpClient().sendMessage(objectMapper.writeValueAsBytes(rawJson));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新好友分组
     * @param friendId 好友ID
     * @param friendCategory 新的好友分组名称
     */
    @Override
    public void updateFriendCategory(Long friendId, String friendCategory) {
        friendDao.updateFriendCategory(UserState.getSingleton().getUser().getUserId(), friendId, friendCategory);
    }
}
