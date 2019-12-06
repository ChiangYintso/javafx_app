package pers.jiangyinzuo.chat.client;

import com.fasterxml.jackson.databind.JsonNode;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option;
/**
 *  �ͻ��˵ľ�������, ������ת��TcpClient���յ�������TcpServer����Ϣ
 *  @author Jiang Yinzuo
 */
public class Broker implements MainBoardController.Contract {
    /**
     * TcpClient���յ���Ϣ����ô˷���, ����Ϣ����Broker����
     * @param jsonNode
     */
    public void receiveMessage(JsonNode jsonNode) {
        String option = JsonHelper.getJsonOption(jsonNode);
        switch (option) {
            case Option.MESSAGE:
                // TODO �յ�������Ϣ
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
