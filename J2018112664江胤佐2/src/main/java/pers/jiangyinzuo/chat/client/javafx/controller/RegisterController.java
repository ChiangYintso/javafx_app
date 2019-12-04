package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pers.jiangyinzuo.chat.common.CustomInfo;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author Jiang Yinzuo
 */
public class RegisterController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    private PasswordField password;
    
    private AccountService accountService;
    
    public RegisterController() {
		this.accountService = new AccountServiceImpl();
	}

    @FXML
    void registerAccount(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("注册提示");
    	if (password.getText().isBlank() || username.getText().isBlank() || repeatPassword.getText().isBlank()) {
    		alert.setContentText("不能为空");
    		alert.showAndWait();
    	} else if (!password.getText().equals(repeatPassword.getText())) {
    		alert.setContentText("两次密码不一样");
    		alert.showAndWait();
    	} else {
    		Long userId = accountService.register(username.getText(), password.getText());
    		if (userId.equals(-1L)) {
    			alert.setContentText("注册失败");
			} else {
				alert.setContentText("注册成功。用户名是" + userId);
			}
    		alert.showAndWait();
    		SceneRouter.closeStage("注册");
    	}
    }
}