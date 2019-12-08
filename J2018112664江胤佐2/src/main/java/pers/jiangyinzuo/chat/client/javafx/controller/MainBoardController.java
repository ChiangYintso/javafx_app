package pers.jiangyinzuo.chat.client.javafx.controller;


import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.components.IndexPaneCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.NoticeCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.javafx.controller.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;


/**
 * @author Jiang Yinzuo
 */
public class MainBoardController implements NoticeCmpController.MainBoardContract {

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
	private Button noticeBtn;

	@FXML
	private Button addBtn;

	@FXML
	private Text onlineTotal;

	@FXML
	private AnchorPane rightPane;

	private FriendService friendService = new FriendServiceImpl();

	private static MainBoardController self;

	/**
	 * 用户的好友列表
	 */
	private List<User> friendList;

	/**
	 * 用户的群聊列表
	 */
	private List<Group> groupList;

	private TreeView<Pane> treeView;

	private Stage mainBoardStage;

	private int newMessageCount = 0;

	public ImageView getAvatar() {
		return avatar;
	}

	public Text getUsername() {
		return username;
	}

	public Button getNoticeBtn() {
		return noticeBtn;
	}

	public Text getOnlineTotal() {
		return onlineTotal;
	}

	public TreeView<Pane> getTreeView() {
		return treeView;
	}

	public Stage getMainBoardStage() {
		return mainBoardStage;
	}

	public interface Publisher {

		/**
		 * 更新全网在线人数
		 * @param jsonNode JSON
		 */
		default void onUpdateOnlineTotal(JsonNode jsonNode) {
			if (Platform.isFxApplicationThread()) {
				int totalCount = jsonNode.get("totalCount").asInt();
				self.onlineTotal.setText("全网" + totalCount + "人在线");
			} else {
				Platform.runLater(() -> {
					int totalCount = jsonNode.get("totalCount").asInt();
					self.onlineTotal.setText("全网" + totalCount + "人在线");
				});
			}
		}

		/**
		 * 新的消息, 可以是ADD_FRIEND, AGREE_TO_ADD_FRIEND等
		 * @param jsonNode JSON
		 */
		default void onNewNoticeReceived(JsonNode jsonNode) {
			// 同意加为好友
			if (JsonHelper.getJsonOption(jsonNode).equals(JsonHelper.Option.AGREE_TO_ADD_FRIEND)) {
				UpdateUiUtil.updateUi(() -> self.loadTreeView());
			}
			UpdateUiUtil.updateUi(() -> {
				self.newMessageCount++;
				self.noticeBtn.setText("[" + ControllerProxy.getMainBoardController().newMessageCount + "]条新消息");
			});
		}
	}

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("查找面板", "AddBoard.fxml");
	}

	/**
	 * 更新用户信息
	 */
	public void updateUserInfo() {
		UpdateUiUtil.updateUi(() -> {
			User user = UserState.getSingleton().getUser();
			this.username.setText(user.getUserName());
			Image image = new Image(user.getAvatar());
			avatar.setImage(image);
		});
	}

	@FXML
	public void initialize() {
		updateUserInfo();
		this.mainBoardStage = SceneRouter.getStage("网络聊天室");
		ControllerProxy.setMainBoardController(this);
		self = this;

		this.loadTreeView();

		// 询问好友在线情况
		Main.getClientThreadPool().execute(() -> friendService.requestForFriendsStatus(UserState.getSingleton().getUser().getUserId()));

		mainBoardStage.setOnCloseRequest((event) -> {
			System.out.println("主界面关闭");
			Main.exit();
		});
	}

	@FXML
	void showNotice(ActionEvent event) {
		try {
			SceneRouter.showStage("通知", "NoticeBoard.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TreeItem<Pane> loadIndexTreeItem(String text) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "IndexPaneCmp.fxml"));
		Pane pane = null;
		try {
			pane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		((IndexPaneCmpController)fxmlLoader.getController()).init(text);
		TreeItem<Pane> treeItem = new TreeItem<>(pane);
		treeItem.setExpanded(true);
		return treeItem;
	}

	private <T extends SessionCardCmpController.Session> TreeItem<Pane> loadCardItem(T session) {
		FXMLLoader fxmlLoader;
		Pane pane = null;
		fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "SessionCardCmp.fxml"));
		try {
			pane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SessionCardCmpController sessionCardCmpController = fxmlLoader.getController();
		sessionCardCmpController.init(session);
		return new TreeItem<>(pane);
	}

	/**
	 * 加载好友和群聊列表
	 */
	@Override
	public void loadTreeView() {
		User user = UserState.getSingleton().getUser();
		this.friendList = user.getFriendList();

		// 从数据库更新群聊列表
		this.groupList = user.getGroupList(true);
		rightPane.getChildren().removeAll();

		// 初始化会话列表
		TreeItem<Pane> rootItem = loadIndexTreeItem("会话列表");

		// 初始化好友列表
		TreeItem<Pane> friendTreeItem = loadIndexTreeItem("我的好友");

		Map<String, Set<User>> friendCategories = new HashMap<>(20);

		// 初始化好友分组名——好友集合的HashMap
		for (User friend : friendList) {
			if (!friendCategories.containsKey(friend.getFriendCategory())) {
				friendCategories.put(friend.getFriendCategory(), new HashSet<>(1));
			}
			friendCategories.get(friend.getFriendCategory()).add(friend);
		}
		for (Map.Entry<String, Set<User>> kv : friendCategories.entrySet()) {
			// 初始化好友分组
			TreeItem<Pane> friendCategory = loadIndexTreeItem(kv.getKey());

			for (User friend : kv.getValue()) {
				friendCategory.getChildren().add(loadCardItem(friend));
			}
			friendTreeItem.getChildren().add(friendCategory);
		}

		// 初始化群聊列表
		TreeItem<Pane> groupTreeItem = loadIndexTreeItem("我的群聊");
		for (Group group : groupList) {
			groupTreeItem.getChildren().add(loadCardItem(group));
		}

		rootItem.getChildren().addAll(friendTreeItem, groupTreeItem);

		// 初始化treeView, 并加载到页面
		treeView = new TreeView<>(rootItem);
		treeView.setMinWidth(350);
		treeView.setMaxWidth(1000);
		treeView.setMinHeight(650);
		rightPane.getChildren().add(treeView);
	}

	@FXML
	void showSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("设置", "SettingBoard.fxml");
	}
}