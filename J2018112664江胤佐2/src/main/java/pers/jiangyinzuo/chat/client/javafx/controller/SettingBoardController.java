package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class SettingBoardController {

    @FXML
    private ImageView avator;

    @FXML
    private Button saveProfileBtn;

    @FXML
    private Label idField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private TextArea introTextArea;

    private AccountService accountService = new AccountServiceImpl();

    @FXML
    public void initialize() {
        User user = UserState.getSingleton().getUser();
        userNameField.setText(user.getUserName());
        introTextArea.setText(user.getIntro());
        idField.setText(user.getUserId().toString());
		avator.setImage(new Image(user.getAvatar()));
    }

    @FXML
    void saveProfile(ActionEvent event) {
        if (userNameField.getText() == null || userNameField.getText().isBlank()) {
            CustomAlertBoard.showAlert("用户名不能为空");
            return;
        }
        if (passwordField.getText() == null || passwordField.getText().isBlank()) {
            CustomAlertBoard.showAlert("密码不能为空");
            return;
        }
        if (repeatPasswordField.getText() == null || passwordField.getText().isBlank()) {
            CustomAlertBoard.showAlert("请重复填写密码");
        }
        if (!repeatPasswordField.getText().equals(passwordField.getText())) {
            CustomAlertBoard.showAlert("两次密码不一致");
        }
        UserState.getSingleton().getUser().setUserName(userNameField.getText());
        UserState.getSingleton().getUser().setPassword(passwordField.getText());
        UserState.getSingleton().getUser().setIntro(introTextArea.getText());
        accountService.updateUserInfo(UserState.getSingleton().getUser());
        CustomAlertBoard.showAlert("修改成功");
        ControllerProxy.getMainBoardController().updateUserInfo();

        byte[] finalBytes = JsonHelper.writeFriendStatusChangedBytes(UserState.getSingleton().getUser());

        Main.getClientThreadPool().execute(() -> Main.getTcpClient().sendMessage(finalBytes));
    }
}
