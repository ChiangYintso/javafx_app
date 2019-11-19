package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.LoginServiceImpl;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private RadioButton studentRadio;

    @FXML
    private RadioButton teacherRadio;
    
    private LoginService loginService; 

    @FXML
    void login(ActionEvent event) {
    	if (studentRadio.isSelected()) {
    		
    	}
    }
    
    @FXML
    public void initialize() {
    	loginService = new LoginServiceImpl();
    }

}
