package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.entity.Group;
import main.java.pers.jiangyinzuo.chat.entity.Session;
import main.java.pers.jiangyinzuo.chat.entity.User;
import main.java.pers.jiangyinzuo.chat.service.FriendService;
import main.java.pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;
import main.java.pers.jiangyinzuo.chat.ui.javafx.common.CustomAlertBoard;
import main.java.pers.jiangyinzuo.chat.ui.javafx.controller.components.SessionCardCmpController;
import main.java.pers.jiangyinzuo.chat.ui.state.UserInfo;

public class MainBoardController {

	private static String DEFAULT_AVATOR_URL = "file:src/main/java/pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png";
    @FXML
    private AnchorPane userinfoPane;

    @FXML
    private ImageView avator;

    @FXML
    private Text username;

    @FXML
    private Button showGroupsOrFriendsBtn;
    
    @FXML
    private Button addBtn;

    @FXML
    private VBox friendListContainer;
    
    // 真：展示群聊列表；假：展示好友列表
    private boolean showGroups;
    
    private FriendService friendService;
    
    private List<User> friendList;
    
    @FXML
    void addFriendOrGroup(ActionEvent event) {

    }

    @FXML
    void showGroupsOrFriends(ActionEvent event) throws IOException {
    	if (showGroups) {
    		this.<User>loadSessionList(UserInfo.getSingleton().getUser().getFriendList());
    		this.showGroupsOrFriendsBtn.setText("显示群聊");
    	} else {
    		this.<Group>loadSessionList(UserInfo.getSingleton().getUser().getGroupList());
    		this.showGroupsOrFriendsBtn.setText("显示好友");
    	}
    	this.showGroups = !this.showGroups;
    }
    
    @FXML
    public void initialize() {
    	this.showGroups = false;
    	this.friendService = new FriendServiceImpl();
    	friendList = this.friendService.getFriendList(UserInfo.getSingleton().getUser());
    	this.username.setText(UserInfo.getSingleton().getUser().getUserName());
    	UserInfo.getSingleton().getUser().setFriendList(friendList);
    	Image image = new Image(DEFAULT_AVATOR_URL);
    	avator.setImage(image);
    	try {
    		this.<User>loadSessionList(friendList);
    	} catch (IOException e) {
			CustomAlertBoard.showAlert("IO异常");
		}
    	
    }
    
    // 动态加载好友或群聊列表
    private <T extends Session> void loadSessionList(List<T> sessionList) throws IOException {
    	this.friendListContainer.getChildren().clear();
    	
    	if (sessionList == null || sessionList.size() == 0) {
    		Text text = new Text("列表为空");
    		this.friendListContainer.getChildren().add(text);
    	}
    	
    	for (T t : sessionList) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "SessionCardCmp.fxml"));
    		Pane pane = fxmlLoader.load();
    		SessionCardCmpController controller = fxmlLoader.getController();
    		controller.init(DEFAULT_AVATOR_URL, t.getSessionName());
    		this.friendListContainer.getChildren().add(pane);
    	}
    }
}