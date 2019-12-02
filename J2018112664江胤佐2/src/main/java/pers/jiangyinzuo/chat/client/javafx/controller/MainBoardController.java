package pers.jiangyinzuo.chat.client.javafx.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class MainBoardController {

	@FXML
	private AnchorPane userinfoPane;

	@FXML
	private ImageView avator;

	@FXML
	private Button userSettingBtn;

	@FXML
	private Text username;

	@FXML
	private Button showGroupsOrFriendsBtn;

	@FXML
	private Button addBtn;

	@FXML
	private VBox friendListContainer;

	/**
	 * true：展示群聊列表；false：展示好友列表
 	 */
	private boolean showGroups;

	private FriendService friendService;

	private List<User> friendList;

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("查找面板", "AddBoard.fxml");
	}

	@FXML
	void showGroupsOrFriends(ActionEvent event) throws IOException {
		if (showGroups) {
			this.<User>loadConversationList(UserState.getSingleton().getUser().getFriendList());
			this.showGroupsOrFriendsBtn.setText("显示群聊");
		} else {
			this.<Group>loadConversationList(UserState.getSingleton().getUser().getGroupList());
			this.showGroupsOrFriendsBtn.setText("显示好友");
		}
		this.showGroups = !this.showGroups;
	}

	@FXML
	public void initialize() {
		User user = UserState.getSingleton().getUser();
		this.showGroups = false;
		this.friendService = new FriendServiceImpl();
		friendList = this.friendService.getFriendList(user);
		this.username.setText(user.getUserName());

		Image image = new Image(user.getAvatar());
		avator.setImage(image);
		try {
			this.<User>loadConversationList(friendList);
		} catch (IOException e) {
			CustomAlertBoard.showAlert("IO异常");
		}

	}

	/**
	 * 动态加载好友或群聊列表
	 */
	private <T> void loadConversationList(List<T> sessionList) throws IOException {
		this.friendListContainer.getChildren().clear();

		if (sessionList == null || sessionList.size() == 0) {
			Text text = new Text("列表为空");
			this.friendListContainer.getChildren().add(text);
		}

		int i = 0;
		for (T t : sessionList) {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("../scenes/components/" + "SessionCardCmp.fxml"));
			Pane pane = fxmlLoader.load();
			if ((i & 1) == 0) {
				pane.setStyle("-fx-background-color: #cceedd");
			}
			SessionCardCmpController controller = fxmlLoader.getController();
			controller.init(t);
			this.friendListContainer.getChildren().add(pane);
			++i;
		}
	}

	@FXML
	void showUserSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("设置", "UserSetting.fxml");
	}
}