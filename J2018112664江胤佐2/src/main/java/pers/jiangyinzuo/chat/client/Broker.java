package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option;
/**
 *  客户端的经纪人类, 负责处理、转发TcpClient接收到的来自TcpServer的消息
 *  @author Jiang Yinzuo
 */
public class Broker implements MainBoardController.Contract {
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
                SessionState.notifySession(jsonNode);
                break;
            case Option.UPDATE_ONLINE_TOTAL:
                this.onUpdateOnlineTotal(jsonNode);
                break;
            case Option.ADD_FRIEND:
            case Option.AGREE_TO_ADD_FRIEND:
                this.onNewNoticeReceived(jsonNode);
            default:
                break;
        }
    }
}
