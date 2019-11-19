package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.common.CustomInfo;
import main.java.pers.jiangyinzuo.chat.service.AccountService;
import main.java.pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import main.java.pers.jiangyinzuo.chat.ui.javafx.common.CustomAlertBoard;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;

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
    		CustomAlertBoard.showAlert("²»ÄÜÎª¿Õ");
    	} else {
    		accountService = new AccountServiceImpl();
    		CustomInfo customInfo = accountService.login(username.getText(), password.getText());
    		if (customInfo.getStatus() != (short) 200) {
    			CustomAlertBoard.showAlert("ÕËºÅ»òÃÜÂë´íÎó");
    		} else {
    			CustomAlertBoard.showAlert("µÇÂ¼³É¹¦");
    			SceneRouter.closeStage("µÇÂ¼");
    			SceneRouter.showStage("ÍøÂçÁÄÌìÊÒ", "MainBoard.fxml");
    		}
    	}
    }

    @FXML
    void register(ActionEvent event) throws IOException {
    	SceneRouter.showStage("×¢²á", "Register.fxml");
    }

}

