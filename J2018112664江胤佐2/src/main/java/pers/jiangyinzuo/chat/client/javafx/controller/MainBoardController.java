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
	 * true：展示群聊列表；false：展示好友列表
 	 */
	private boolean showGroups;

	private FriendService friendService;

	/**
	 * 用户的好友列表
	 */
	private List<User> friendList;

	private TreeView<String> treeView;

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("查找面板", "AddBoard.fxml");
	}

	@FXML
	void showGroupsOrFriends(ActionEvent event) throws IOException {
		if (showGroups) {
			this.showGroupsOrFriendsBtn.setText("显示群聊");
		} else {
			this.showGroupsOrFriendsBtn.setText("显示好友");
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
	 * 加载好友和群聊列表
	 */
	private void loadTreeView() {
		TreeItem<String> rootItem = new TreeItem<>("会话列表");
		rootItem.setExpanded(true);

		// 初始化好友列表
		TreeItem<String> friendTreeItem = new TreeItem<>("我的好友");
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

		// 初始化群聊列表
		TreeItem<String> groupTreeItem = new TreeItem<>("我的群聊");
		rootItem.getChildren().addAll(friendTreeItem, groupTreeItem);

		// 初始化treeView, 并加载到页面
		treeView = new TreeView<>(rootItem);
		rightPane.getChildren().add(treeView);
	}

	@FXML
	void showUserSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("设置", "UserSetting.fxml");
	}
}