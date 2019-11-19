package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.ui.javafx.common.CustomAlertBoard;

public class MainBoardController {

    @FXML
    private AnchorPane userinfoPane;
    
    @FXML
    private ImageView avator;

    @FXML
    private Text username;

    @FXML
    private VBox friendListContainer;
    
    @FXML
    public void initialize() {
    	Image image = new Image("file:src/main/java/pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png");
    	avator.setImage(image);
    }
}