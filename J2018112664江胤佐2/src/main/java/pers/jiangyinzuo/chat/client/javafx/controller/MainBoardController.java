package pers.jiangyinzuo.chat.client.javafx.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;

public class MainBoardController {

	private static final String DEFAULT_AVATAR_URL = "file:src/main/java/pers/jiangyinzuo/chat/ui/javafx/scenes/static/avatar.png";
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

	// �棺չʾȺ���б��٣�չʾ�����б�
	private boolean showGroups;

	private FriendService friendService;

	private List<User> friendList;

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("�������", "AddBoard.fxml");
	}

//	@FXML
//	void showGroupsOrFriends(ActionEvent event) throws IOException {
//		if (showGroups) {
//			this.<User>loadSessionList(UserInfo.getSingleton().getUser().getFriendList());
//			this.showGroupsOrFriendsBtn.setText("��ʾȺ��");
//		} else {
//			this.<Group>loadSessionList(UserInfo.getSingleton().getUser().getGroupList());
//			this.showGroupsOrFriendsBtn.setText("��ʾ����");
//		}
//		this.showGroups = !this.showGroups;
//	}

//	@FXML
//	public void initialize() {
//		User user = UserInfo.getSingleton().getUser();
//		this.showGroups = false;
//		this.friendService = new FriendServiceImpl();
//		friendList = this.friendService.getFriendList(user);
//		this.username.setText(user.getUserName());
//		UserInfo.getSingleton().getUser().setFriendList(friendList);
//
//		Image image = new Image(user.getAvatarUrl());
//		avator.setImage(image);
//		try {
//			this.<User>loadSessionList(friendList);
//		} catch (IOException e) {
//			CustomAlertBoard.showAlert("IO�쳣");
//		}
//
//	}

	// ��̬���غ��ѻ�Ⱥ���б�
//	private <T extends Session> void loadSessionList(List<T> sessionList) throws IOException {
//		this.friendListContainer.getChildren().clear();
//
//		if (sessionList == null || sessionList.size() == 0) {
//			Text text = new Text("�б�Ϊ��");
//			this.friendListContainer.getChildren().add(text);
//		}
//
//		int i = 0;
//		for (T t : sessionList) {
//			FXMLLoader fxmlLoader = new FXMLLoader(
//					getClass().getResource("../scenes/components/" + "SessionCardCmp.fxml"));
//			Pane pane = fxmlLoader.load();
//			if ((i & 1) == 0) {
//				pane.setStyle("-fx-background-color: #cceedd");
//			}
//			SessionCardCmpController controller = fxmlLoader.getController();
//			controller.init(t);
//			this.friendListContainer.getChildren().add(pane);
//			++i;
//		}
//	}

	@FXML
	void showUserSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("����", "UserSetting.fxml");
	}
}