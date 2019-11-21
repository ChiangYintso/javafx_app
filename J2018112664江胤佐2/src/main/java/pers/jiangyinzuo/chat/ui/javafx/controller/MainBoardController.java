package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button showGroupsOrFriendsBtn;

    @FXML
    private VBox friendListContainer;
    
    // 真：展示群聊列表；假：展示好友列表
    private boolean showGroups;

    @FXML
    void showGroupsOrFriends(ActionEvent event) {
    	if (showGroups) {
    		showGroupsOrFriendsBtn.setText("显示好友");
    	} else {
    		showGroupsOrFriendsBtn.setText("显示群聊");
    	}
    	this.showGroups = !this.showGroups;
    }
    
    @FXML
    public void initialize() {
    	this.showGroups = false;
    	Image image = new Image("file:src/main/java/pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png");
    	avator.setImage(image);
    }
}