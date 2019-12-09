package pers.jiangyinzuo.chat.client.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;

/**
 * @author Jiang Yinzuo
 */
public class MessageRecordCmpController implements FxController {

    @FXML
    private Text sendFromText;

    @FXML
    private Text messageContentText;

    @FXML
    private Text sendTimeText;

    /**
     * ≥ı ºªØ
     *
     * @param params sendFrom°¢messageContent°¢sendTime
     */
    @Override
    public void init(Object... params) {
        sendFromText.setText((String)params[0]);
        messageContentText.setText((String)params[1]);
        sendTimeText.setText((String)params[2]);
    }
}
