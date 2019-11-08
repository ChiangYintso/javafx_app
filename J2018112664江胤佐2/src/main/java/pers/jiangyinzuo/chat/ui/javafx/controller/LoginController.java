package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private Text title;

    @FXML
    void changeColor(ActionEvent event) {
    	title.setScaleX(3);
    	
    }

}

