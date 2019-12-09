package pers.jiangyinzuo.chat.client.javafx.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.client.state.UserState;

import static pers.jiangyinzuo.chat.client.javafx.Main.startTcpClient;

/**
 * @author Jiang Yinzuo
 */
public class LoginController {
    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private Button retrievePasswordBtn;

    @FXML
    private TextField userIdStr;

    @FXML
    private Text title;

    @FXML
    private PasswordField password;

    private AccountService accountService;

    @FXML
    void login(ActionEvent event) throws IOException {

        if (userIdStr.getText().isBlank() || title.getText().isBlank()) {
            CustomAlertBoard.showAlert("����Ϊ��");
        } else {
            long userId;
            try {
                userId = Long.parseLong(userIdStr.getText());
            } catch (Exception e) {
                CustomAlertBoard.showAlert("�˺Ÿ�ʽ����");
                return;
            }
            accountService = new AccountServiceImpl();
            User user = accountService.login(userId, password.getText());
            if (user == null) {
                CustomAlertBoard.showAlert("�˺Ż��������");
            } else {
                startTcpClient(user.getUserId());
                UserState.getSingleton().setUser(user);
                StageManager.closeStage("��¼");
                StageManager.showStage("����������", "MainBoard.fxml", "client");
            }
        }
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        StageManager.showStage("ע��", "Register.fxml", "client");
    }

    @FXML
    void retrievePassword(ActionEvent event) {
        StageManager.showTempStage("�һ�����", "RetrievePassword.fxml", "client");
    }
}

