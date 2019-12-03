package pers.jiangyinzuo.chat.client.javafx.controller;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.state.UserState;
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
	private ImageView avatar;

	@FXML
	private Button userSettingBtn;

	@FXML
	private Text username;

	@FXML
	private Button showGroupsOrFriendsBtn;

	@FXML
	private Button addBtn;

	@FXML
	private AnchorPane rightPane;

	/**
	 * true��չʾȺ���б�false��չʾ�����б�
 	 */
	private boolean showGroups;

	private FriendService friendService;

	/**
	 * �û��ĺ����б�
	 */
	private List<User> friendList;

	private TreeView<String> treeView;

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("�������", "AddBoard.fxml");
	}

	@FXML
	void showGroupsOrFriends(ActionEvent event) throws IOException {
		if (showGroups) {
			this.showGroupsOrFriendsBtn.setText("��ʾȺ��");
		} else {
			this.showGroupsOrFriendsBtn.setText("��ʾ����");
		}
		this.showGroups = !this.showGroups;
	}

	@FXML
	public void initialize() {
		User user = UserState.getSingleton().getUser();
		this.showGroups = false;
		this.friendService = new FriendServiceImpl();
		this.friendList = user.getFriendList();
		this.username.setText(user.getUserName());

		Image image = new Image(user.getAvatar());
		avatar.setImage(image);
		this.loadTreeView();
	}

	/**
	 * ���غ��Ѻ�Ⱥ���б�
	 */
	private void loadTreeView() {
		TreeItem<String> rootItem = new TreeItem<>("�Ự�б�");
		rootItem.setExpanded(true);

		// ��ʼ�������б�
		TreeItem<String> friendTreeItem = new TreeItem<>("�ҵĺ���");
		Map<String, Set<User>> friendCategories = new HashMap<>(20);

		for (User friend : friendList) {
			if (!friendCategories.containsKey(friend.getFriendCategory())) {
				friendCategories.put(friend.getFriendCategory(), new HashSet<>(1));
			}
			friendCategories.get(friend.getFriendCategory()).add(friend);
		}
		for (Map.Entry<String, Set<User>> kv : friendCategories.entrySet()) {
			TreeItem<String> friendCategory = new TreeItem<>(kv.getKey());
			for (User friend : kv.getValue()) {
				TreeItem<String> friendItem = new TreeItem<>(friend.getUserName(),
						new ImageView(new Image(friend.getAvatar())));
				friendCategory.getChildren().add(friendItem);
			}
			friendTreeItem.getChildren().add(friendCategory);
		}

		// ��ʼ��Ⱥ���б�
		TreeItem<String> groupTreeItem = new TreeItem<>("�ҵ�Ⱥ��");
		rootItem.getChildren().addAll(friendTreeItem, groupTreeItem);

		// ��ʼ��treeView, �����ص�ҳ��
		treeView = new TreeView<>(rootItem);
		rightPane.getChildren().add(treeView);
	}

	@FXML
	void showUserSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("����", "UserSetting.fxml");
	}
}