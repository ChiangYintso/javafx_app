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
    	alert.setTitle("ע����ʾ");
    	if (password.getText().isBlank() || username.getText().isBlank() || repeatPassword.getText().isBlank()) {
    		alert.setContentText("����Ϊ��");
    		alert.showAndWait();
    	} else if (!password.getText().equals(repeatPassword.getText())) {
    		alert.setContentText("�������벻һ��");
    		alert.showAndWait();
    	} else {
    		Long userId = accountService.register(username.getText(), password.getText());
    		if (userId.equals(-1L)) {
    			alert.setContentText("ע��ʧ��");
			} else {
				alert.setContentText("ע��ɹ����û�����" + userId);
			}
    		alert.showAndWait();
    		SceneRouter.closeStage("ע��");
    	}
    }
}