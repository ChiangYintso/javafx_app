package pers.jiangyinzuo.chat.server.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.domain.dto.LoginDTO;

import java.time.LocalDate;

public class LogCmpController implements FxController {

    @FXML
    private Text userIdText;

    @FXML
    private Text logTimeText;


    @Override
    public void init(Object... params) {
        LoginDTO loginDTO = (LoginDTO) params[0];
        userIdText.setText(loginDTO.getUserId().toString());
        logTimeText.setText(loginDTO.getLogTime().toLocalDateTime().toString());
    }
}
