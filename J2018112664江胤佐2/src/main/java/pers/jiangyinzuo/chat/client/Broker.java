package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;
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
        switch (option) {
            case Option.MESSAGE:
                // TODO 收到好友消息
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
        System.out.println(jsonNode);
    }
}
