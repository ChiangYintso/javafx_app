package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option;
/**
 *  �ͻ��˵ľ�������, ������ת��TcpClient���յ�������TcpServer����Ϣ
 *  @author Jiang Yinzuo
 */
public class Broker implements MainBoardController.Publisher, SessionCardCmpController.Publisher {
    /**
     * TcpClient���յ���Ϣ����ô˷���, ����Ϣ����Broker����
     * @param jsonNode
     */
    public void receiveMessage(JsonNode jsonNode) {
        String option = JsonHelper.getJsonOption(jsonNode);
        System.out.println(jsonNode);
        switch (option) {
            case Option.CONNECTION_SUCCESS:
                System.out.println("### ���ӳɹ� ###");
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
     * ���º����������
     * �յ���JSON:
     * {
     * "option": Option.FRIENDS_ONLINE_STATUS,
     * "sendTo": <���͸��ͻ��˵�id>,
     * "onLineList": <���ߵĺ���id>
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
