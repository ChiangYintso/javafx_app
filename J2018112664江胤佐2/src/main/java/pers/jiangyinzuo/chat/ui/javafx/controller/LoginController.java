package pers.jiangyinzuo.chat.ui.javafx.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.CustomInfo;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import pers.jiangyinzuo.chat.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.ui.state.UserInfo;

public class LoginController {
    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField username;

    @FXML
    private Text title;

    @FXML
    private PasswordField password;
    
    private AccountService accountService;

    @FXML
    void login(ActionEvent event) throws IOException {
    	
    	if (username.getText().isBlank() || title.getText().isBlank()) {
    		CustomAlertBoard.showAlert("����Ϊ��");
    	} else {
    		accountService = new AccountServiceImpl();
    		CustomInfo customInfo = accountService.login(username.getText(), password.getText());
    		if (customInfo.getStatus() != (short) 200) {
    			CustomAlertBoard.showAlert("�˺Ż��������");
    		} else {
    			SceneRouter.closeStage("��¼");
    			UserInfo.getSingleton().setUser((User)customInfo.getEntity());
    			SceneRouter.showStage("����������", "MainBoard.fxml");
    		}
    	}
    }

    @FXML
    void register(ActionEvent event) throws IOException {
    	SceneRouter.showStage("ע��", "Register.fxml");
    }

}

