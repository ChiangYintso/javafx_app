package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.SceneRouter;


/**
 * @author Jiang Yinzuo
 */
public class LoginController {

    @FXML
    private Pane layout;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private Text title;

    @FXML
    void login(ActionEvent event) {
        if (password.getText() == null || !"123456".equals(password.getText())) {
            CustomAlertBoard.showAlert("�������[��ʾ: ����Ϊ123456]");
        } else {
            SceneRouter.closeStage("����˵�¼");
            SceneRouter.showStage("����������", "MainBoard.fxml", "server");
        }
    }

}