package pers.jiangyinzuo.chat.client.javafx.controller;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;

import static pers.jiangyinzuo.chat.client.javafx.Main.getTcpClient;

/**
 * @author Jiang Yinzuo
 */
public class MainBoardController {

	private static MainBoardController singleton;

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

	private FriendService friendService;

	/**
	 * �û��ĺ����б�
	 */
	private List<User> friendList;

	/**
	 * �û���Ⱥ���б�
	 */
	private List<Group> groupList;

	private TreeView<String> treeView;

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

	public TreeView<String> getTreeView() {
		return treeView;
	}

	public Stage getMainBoardStage() {
		return mainBoardStage;
	}

	public interface Contract {
		/**
		 * ����ȫ����������
		 * @param jsonNode JSON
		 */
		default void onUpdateOnlineTotal(JsonNode jsonNode) {
			if (Platform.isFxApplicationThread()) {
				int totalCount = jsonNode.get("totalCount").asInt();
				singleton.onlineTotal.setText("ȫ��" + totalCount + "������");
			} else {
				Platform.runLater(() -> {
					int totalCount = jsonNode.get("totalCount").asInt();
					singleton.onlineTotal.setText("ȫ��" + totalCount + "������");
				});
			}

		}

		/**
		 * �µ���Ϣ
		 * @param jsonNode JSON
		 */
		default void onNewNoticeReceived(JsonNode jsonNode) {
			if (Platform.isFxApplicationThread()) {
				singleton.newMessageCount++;
				singleton.noticeBtn.setText("[" + singleton.newMessageCount + "]������Ϣ");
			} else {
				Platform.runLater(() -> {
					singleton.newMessageCount++;
					singleton.noticeBtn.setText("[" + singleton.newMessageCount + "]������Ϣ");
				});
			}
		}
	}

	@FXML
	void addFriendOrGroup(ActionEvent event) {
		SceneRouter.showTempStage("�������", "AddBoard.fxml");
	}

	@FXML
	public void initialize() {
		User user = UserState.getSingleton().getUser();
		this.friendService = new FriendServiceImpl();
		this.friendList = user.getFriendList();
		this.groupList = user.getGroupList();
		this.username.setText(user.getUserName());
		this.mainBoardStage = SceneRouter.getStage("����������");

		singleton = this;

		Image image = new Image(user.getAvatar());
		avatar.setImage(image);
		this.loadTreeView();
		QueryOnlineTotalHandler queryOnlineTotalHandler = new QueryOnlineTotalHandler();
		queryOnlineTotalHandler.setName("QueryOnlineTotal");

		mainBoardStage.setOnCloseRequest((event) -> {
			queryOnlineTotalHandler.interrupt();
			System.out.println("������ر�");
			Main.exit();
		});
		Main.getClientThreadPool().execute(queryOnlineTotalHandler);
	}

	@FXML
	void showNotice(ActionEvent event) {

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

		// ��ʼ�����ѷ������������Ѽ��ϵ�HashMap
		for (User friend : friendList) {
			if (!friendCategories.containsKey(friend.getFriendCategory())) {
				friendCategories.put(friend.getFriendCategory(), new HashSet<>(1));
			}
			friendCategories.get(friend.getFriendCategory()).add(friend);
		}
		for (Map.Entry<String, Set<User>> kv : friendCategories.entrySet()) {
			TreeItem<String> friendCategory = new TreeItem<>(kv.getKey());
			for (User friend : kv.getValue()) {
				ImageView imageView = new ImageView(new Image(friend.getAvatar()));
				imageView.setFitWidth(30);
				imageView.setFitHeight(30);
				TreeItem<String> friendItem = new TreeItem<>(friend.getUserName(),
						imageView);
				friendCategory.getChildren().add(friendItem);
			}
			friendTreeItem.getChildren().add(friendCategory);
		}

		// ��ʼ��Ⱥ���б�
		TreeItem<String> groupTreeItem = new TreeItem<>("�ҵ�Ⱥ��");
		for (Group group : groupList) {
			TreeItem<String> treeItem = new TreeItem<>(group.getGroupName());
			groupTreeItem.getChildren().add(treeItem);
		}

		rootItem.getChildren().addAll(friendTreeItem, groupTreeItem);

		// ��ʼ��treeView, �����ص�ҳ��
		treeView = new TreeView<>(rootItem);
		rightPane.getChildren().add(treeView);
	}

	@FXML
	void showUserSettingBoard(ActionEvent event) {
		SceneRouter.showTempStage("����", "UserSetting.fxml");
	}

	/**
	 * ѯ�����������߳�
	 */
	private static class QueryOnlineTotalHandler extends Thread {
		/**
		 * ���͸�����˵���Ϣ
		 */
		private byte[] message;

		QueryOnlineTotalHandler() {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = new HashMap<>(10);
			map.put("option", JsonHelper.Option.ASK_FOR_ONLINE_TOTAL);
			map.put("sendTo", UserState.getSingleton().getUser().getUserId());
			try {
				message = objectMapper.writeValueAsBytes(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (Main.isOn) {
				try {
					sleep(10000);
					if (Main.isOn) {
						getTcpClient().sendMessage(message);
						System.out.println("ѯ����������");
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
}