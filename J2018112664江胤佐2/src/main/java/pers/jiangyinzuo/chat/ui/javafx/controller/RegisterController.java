package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.java.pers.jiangyinzuo.chat.common.CustomInfo;
import main.java.pers.jiangyinzuo.chat.service.AccountService;
import main.java.pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    	alert.setTitle("ע����ʾ");
    	if (password.getText().isBlank() || username.getText().isBlank() || repeatPassword.getText().isBlank()) {
    		alert.setContentText("����Ϊ��");
    	} else if (!password.getText().equals(repeatPassword.getText())) {
    		alert.setContentText("�������벻һ��");
    	} else {
    		CustomInfo customInfo = accountService.register(username.getText(), password.getText());
    		alert.setContentText(customInfo.getErrInfo());
    	}
    	alert.showAndWait();
    	SceneRouter.closeStage("ע��");
    }


}