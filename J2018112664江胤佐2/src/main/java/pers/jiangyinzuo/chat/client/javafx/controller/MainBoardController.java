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
import pers.jiangyinzuo.chat.common.javafx.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;


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
	private Button foundGroupBtn;

	@FXML
	private Text onlineTotal;

	@FXML
	private AnchorPane rightPane;

	private FriendService friendService = new FriendServiceImpl();

	private static MainBoardController self;

	/**
	 * �û��ĺ����б�
	 */
	private List<User> friendList;

	/**
	 * �û���Ⱥ���б�
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
		 * ����ȫ����������
		 * @param jsonNode JSON
		 */
		default void onUpdateOnlineTotal(JsonNode jsonNode) {
			if (Platform.isFxApplicationThread()) {
				int totalCount = jsonNode.get("totalCount").asInt();
				self.onlineTotal.setText("ȫ��" + totalCount + "������");
			} else {
				Platform.runLater(() -> {
					int totalCount = jsonNode.get("totalCount").asInt();
					self.onlineTotal.setText("ȫ��" + totalCount + "������");
				});
			}
		}

		/**
		 * �µ���Ϣ, ������ADD_FRIEND, AGREE_TO_ADD_FRIEND, GROUP_MANAGEMENT
		 * @param jsonNode JSON
		 */
		default void onNewNoticeReceived(JsonNode jsonNode) {
			String option = JsonHelper.getJsonOption(jsonNode);
			// ͬ���Ϊ����
			if (option.equals(JsonHelper.Option.AGREE_TO_ADD_FRIEND) ||
					(option.equals(JsonHelper.Option.FOUND_GROUP_ACCEPTED) ||
							option.equals(JsonHelper.Option.AGREE_TO_JOIN_GROUP))) {
				UpdateUiUtil.updateUi(() -> self.loadTreeView());
			}
			self.increaseNewNoticeCount();
		}
	}

	private void initNewNoticeCount(int newMessageCount) {
		UpdateUiUtil.updateUi(() -> {
			self.noticeBtn.setText("[" + newMessageCount + "]������Ϣ");
		});
	}

	public void increaseNewNoticeCount() {
		UpdateUiUtil.updateUi(() -> {
			self.noticeBtn.setText("[" + ++newMessageCount + "]������Ϣ");
		});
	}

	public void decreaseNewNoticeCount() {
		UpdateUiUtil.updateUi(() -> {
			self.noticeBtn.setText("[" + --newMessageCount + "]������Ϣ");
		});
	}


	@FXML
	void addFriendOrGroup(ActionEvent event) {
		StageManager.showTempStage("�������", "AddBoard.fxml", "client");
	}

	/**
	 * �����û���Ϣ
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
		this.mainBoardStage = StageManager.getStage("����������");
		ControllerProxy.setMainBoardController(this);
		self = this;

		// ���غ���Ⱥ�ķ���
		this.loadTreeView();

		// ����֪ͨ��ť������
		this.newMessageCount = new NoticeServiceImpl().getUnhandledNoticeCount(UserState.getSingleton().getUser().getUserId());
		this.initNewNoticeCount(newMessageCount);

		mainBoardStage.setOnCloseRequest((event) -> {
			System.out.println("������ر�");
			Main.exit();
		});
	}

	/**
	 * �½�Ⱥ��
	 * @param event
	 */
	@FXML
	void foundGroup(ActionEvent event) {
		StageManager.showTempStage("�½�Ⱥ��", "FoundGroupBoard.fxml", "client");
	}

	@FXML
	void showNotice(ActionEvent event) {
		StageManager.showTempStage("֪ͨ", "NoticeBoard.fxml", "client");
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
	 * ���غ��Ѻ�Ⱥ���б�
	 */
	@Override
	public void loadTreeView() {
		User user = UserState.getSingleton().getUser();
		this.friendList = user.getFriendList();

		// �����ݿ����Ⱥ���б�
		this.groupList = user.getGroupList(true);
		rightPane.getChildren().removeAll();

		// ��ʼ���Ự�б�
		TreeItem<Pane> rootItem = loadIndexTreeItem("�Ự�б�");

		// ��ʼ�������б�
		TreeItem<Pane> friendTreeItem = loadIndexTreeItem("�ҵĺ���");

		Map<String, Set<User>> friendCategories = new HashMap<>(20);

		// ��ʼ�����ѷ������������Ѽ��ϵ�HashMap
		for (User friend : friendList) {
			if (!friendCategories.containsKey(friend.getFriendCategory())) {
				friendCategories.put(friend.getFriendCategory(), new HashSet<>(1));
			}
			friendCategories.get(friend.getFriendCategory()).add(friend);
		}
		for (Map.Entry<String, Set<User>> kv : friendCategories.entrySet()) {
			// ��ʼ�����ѷ���
			TreeItem<Pane> friendCategory = loadIndexTreeItem(kv.getKey());

			for (User friend : kv.getValue()) {
				friendCategory.getChildren().add(loadCardItem(friend));
			}
			friendTreeItem.getChildren().add(friendCategory);
		}

		// ��ʼ��Ⱥ���б�
		TreeItem<Pane> groupTreeItem = loadIndexTreeItem("�ҵ�Ⱥ��");
		for (Group group : groupList) {
			groupTreeItem.getChildren().add(loadCardItem(group));
		}

		rootItem.getChildren().addAll(friendTreeItem, groupTreeItem);

		// ��ʼ��treeView, �����ص�ҳ��
		treeView = new TreeView<>(rootItem);
		treeView.setMinWidth(350);
		treeView.setMaxWidth(1000);
		treeView.setMinHeight(650);
		rightPane.getChildren().add(treeView);

		// ѯ�ʺ����������
		Main.getClientThreadPool().execute(() -> friendService.requestForFriendsStatus(UserState.getSingleton().getUser().getUserId()));
	}

	@FXML
	void showSettingBoard(ActionEvent event) {
		StageManager.showTempStage("����", "SettingBoard.fxml", "client");
	}
}