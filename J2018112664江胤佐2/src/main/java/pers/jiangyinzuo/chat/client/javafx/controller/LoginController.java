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
import pers.jiangyinzuo.chat.client.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
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
    private TextField userIdStr;

    @FXML
    private Text title;

    @FXML
    private PasswordField password;
    
    private AccountService accountService;

    @FXML
    void login(ActionEvent event) throws IOException {
    	
    	if (userIdStr.getText().isBlank() || title.getText().isBlank()) {
    		CustomAlertBoard.showAlert("≤ªƒ‹Œ™ø’");
    	} else {
    	    Long userId;
    	    try {
    	        userId = Long.parseLong(userIdStr.getText());
            } catch (Exception e) {
    	        CustomAlertBoard.showAlert("’À∫≈∏Ò Ω¥ÌŒÛ");
                return;
    	    }
    		accountService = new AccountServiceImpl();
    		User user = accountService.login(userId, password.getText());
    		if (user == null) {
    			CustomAlertBoard.showAlert("’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
    		} else {
    		    startTcpClient(user.getUserId());
    		    UserState.getSingleton().setUser(user);
    			SceneRouter.closeStage("µ«¬º");
    			SceneRouter.showStage("Õ¯¬Á¡ƒÃÏ “", "MainBoard.fxml");
    		}
    	}
    }

    @FXML
    void register(ActionEvent event) throws IOException {
    	SceneRouter.showStage("◊¢≤·", "Register.fxml");
    }

}

