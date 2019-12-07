package pers.jiangyinzuo.chat.client.javafx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.components.MessageCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.util.FxmlLoaderUtil;
import pers.jiangyinzuo.chat.client.javafx.controller.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class ChattingBoardController implements SessionState.Distributor {

    @FXML
    private TextArea inputBox;

    @FXML
    private Button sendBtn;

    @FXML
    private Button option;

    @FXML
    private Text sessionNameField;

    @FXML
    private VBox messageBox;

    @FXML
    private ImageView avatar;

    @FXML
    private ScrollPane scrollPane;
    
    private SessionCardCmpController.Session session;

    private User friend = null;

    private Group group = null;

    private MessageService messageService = new MessageServiceImpl();

    @FXML
    void sendMessage(ActionEvent event) {
        byte[] message = new byte[256];
        if (friend != null) {
            try {
                message = JsonHelper.generateByteMessage(1, inputBox.getText(), UserState.getSingleton().getUser().getUserId(), friend.getUserId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        byte[] finalMessage = message;

        FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml", Message.parseToMessageEntity(message), UserState.getSingleton().getUser());
        fxmlLoaderUtil.getController().rightAlign();

        UpdateUiUtil.updateUi(() -> messageBox.getChildren().add(fxmlLoaderUtil.getPane()));

        Main.getClientThreadPool().execute(() -> Main.getTcpClient().sendMessage(finalMessage));
        inputBox.setText("");
    }

    @FXML
    void initialize() {
    	session = SessionState.getSelectedSession();
    	if (session instanceof User) {
    	    friend = (User) session;
        } else if (session instanceof Group) {
    	    group = (Group) session;
        } else {
    	    throw new RuntimeException();
        }
    	avatar.setImage(new Image(session.getAvatar()));
    	sessionNameField.setText(session.getName());
    	this.registerAsDistributor();
    	this.messageBox.setMaxHeight(450);

    	// 当前客户端用户
    	User user = UserState.getSingleton().getUser();

    	// 查询数据库获取聊天记录
    	List<Message> messageList = messageService.queryRecentMessage(user.getUserId(), friend.getUserId());

        List<FlowPane> paneList = new ArrayList<>();

        // 反向遍历列表
        for (int i = messageList.size() - 1; i >= 0; --i) {
            Long sendFromId = messageList.get(i).getSendFrom();

            FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml",
                    messageList.get(i), sendFromId.equals(user.getUserId()) ? user : friend);

            // 用户发的消息放到右边
            if (sendFromId.equals(user.getUserId())) {
                fxmlLoaderUtil.getController().rightAlign();
            }

            paneList.add(fxmlLoaderUtil.getPane());
        }
        UpdateUiUtil.updateUi(() -> messageBox.getChildren().addAll(paneList));

        messageBox.heightProperty().addListener(e -> scrollPane.setVvalue(1));
    }
    
    @FXML
    void showOption(ActionEvent event) {
        SessionState.setSelectedSession(session);
    	SceneRouter.showTempStage("好友详情", "FriendIntroBoard.fxml");
    }

    /**
     * 新的好友消息到来
     *
     * @param rawJson 好友消息
     */
    @Override
    public void onNewFriendMessageArrived(JsonNode rawJson) {
        FlowPane flowPane = null;
        if (friend != null) {
            try {
                flowPane = FxmlLoaderUtil.<FlowPane, MessageCmpController>loadFxComponent("MessageCmp.fxml", rawJson.get("data"), friend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // TODO: 群聊
        FlowPane finalFlowPane = flowPane;
        UpdateUiUtil.updateUi(() -> messageBox.getChildren().add(finalFlowPane));
    }

    /**
     * 注册成为订阅者
     */
    @Override
    public void registerAsDistributor() {
        if (friend != null) {
            SessionState.addToFriendChattingBoardDistributorMap(friend.getUserId(), this);
        } else if (group != null) {
            SessionState.addToGroupChattingBoardDistributorMap(group.getGroupId(), this);
        } else {
            throw new RuntimeException();
        }
    }
}
