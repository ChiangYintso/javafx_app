package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option;
/**
 *  客户端的经纪人类, 负责处理、转发TcpClient接收到的来自TcpServer的消息
 *  @author Jiang Yinzuo
 */
public class Broker implements MainBoardController.Publisher, SessionCardCmpController.Publisher {
    /**
     * TcpClient接收到消息后调用此方法, 将消息传给Broker处理
     * @param jsonNode
     */
    public void receiveMessage(JsonNode jsonNode) {
        String option = JsonHelper.getJsonOption(jsonNode);
        System.out.println(jsonNode);
        switch (option) {
            case Option.CONNECTION_SUCCESS:
                System.out.println("### 连接成功 ###");
                break;
            case Option.MESSAGE:
            case Option.FRIEND_STATUS_CHANGED:
            case Option.GROUP_BLOCK_CHANGED:
                SessionState.notifySession(jsonNode);
                break;
            case Option.UPDATE_ONLINE_TOTAL:
                this.onUpdateOnlineTotal(jsonNode);
                break;
            case Option.ADD_FRIEND:
            case Option.ADD_GROUP:
            case Option.AGREE_TO_ADD_FRIEND:
            case Option.AGREE_TO_JOIN_GROUP:
            case Option.FOUND_GROUP_ACCEPTED:
                this.onNewNoticeReceived(jsonNode);
                break;
            case Option.FRIENDS_ONLINE_STATUS:
                this.onUpdateFriendOnlineStatus(jsonNode);
                break;
            case Option.UPDATE_USER_INFO:
                UserState.getSingleton().getUser().changeBlockStat();
                break;
            default:
                break;
        }
    }

    /**
     * 更新好友上线情况
     * 收到的JSON:
     * {
     * "option": Option.FRIENDS_ONLINE_STATUS,
     * "sendTo": <发送给客户端的id>,
     * "onLineList": <上线的好友id>
     * }
     *
     * @param jsonNode
     */
    @Override
    public void onUpdateFriendOnlineStatus(JsonNode jsonNode) {

        String option = JsonHelper.getJsonOption(jsonNode);
        if (option.equals(JsonHelper.Option.FRIENDS_ONLINE_STATUS)) {
            SessionState.notifyOnlineStatus(jsonNode);
        }

    }
}
