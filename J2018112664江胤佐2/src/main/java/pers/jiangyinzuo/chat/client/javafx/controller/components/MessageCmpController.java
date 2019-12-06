package pers.jiangyinzuo.chat.client.javafx.controller.components;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pers.jiangyinzuo.chat.client.javafx.controller.FxController;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.domain.entity.User;


/**
 * @author Jiang Yinzuo
 */
public class MessageCmpController implements FxController {

    @FXML
    private Text title;

    @FXML
    private Text content;

    private Message message;

    /**
     * ��ʼ��Message
     * @param params ��һ������ΪJsonNode����Message, �ڶ�������Ϊ������Ϣ��User
     */
    @Override
    public void init(Object ...params) {
        if (params[0] instanceof JsonNode) {
            message = Message.parseToMessage((JsonNode) params[0]);
        } else if (params[0] instanceof Message) {
            message = (Message) params[0];
        }
        title.setText(((User)params[1]).getUserName() + " " + message.getSendTime().toLocalDateTime());
        content.setText(message.getMessageContent());
    }

    public void rightAlign() {
        title.setTextAlignment(TextAlignment.RIGHT);
        content.setTextAlignment(TextAlignment.RIGHT);
    }
}
