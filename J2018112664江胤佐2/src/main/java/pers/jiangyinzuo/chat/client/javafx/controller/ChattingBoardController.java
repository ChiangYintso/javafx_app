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
public class ChattingBoardController implements SessionState.Subscriber {

    @FXML
    TextArea inputBox;

    @FXML
    private Button sendBtn;

    @FXML
    private Button option;

    @FXML
    private Text sessionNameField;

    @FXML
    VBox messageBox;

    @FXML
    ImageView avatar;

    @FXML
    ScrollPane scrollPane;

    private SessionCardCmpController.Session session;

    User self = null;

    private Group group = null;

    MessageService messageService = new MessageServiceImpl();

    private SessionHandler sessionHandler = null;

    interface SessionHandler extends SessionState.Subscriber {
        List<Message> getMessage();

        void sendMessage();
    }

    @FXML
    void sendMessage(ActionEvent event) {
        sessionHandler.sendMessage();
    }

    @FXML
    void initialize() {
        session = SessionState.getSelectedSession();

        // 注册SessionHandler
        if (session instanceof User) {
            sessionHandler = new FriendHandler((User) session, this);
        } else if (session instanceof Group) {
            sessionHandler = new GroupHandler((Group) session, this);
        } else {
            throw new RuntimeException();
        }

        avatar.setImage(new Image(session.getAvatar()));
        sessionNameField.setText(session.getSessionName());
        this.registerAsSubscriber();
        this.messageBox.setMaxHeight(450);

        // 当前客户端用户
        this.self = UserState.getSingleton().getUser();

        // 查询数据库获取聊天记录
        List<Message> messageList = sessionHandler.getMessage();

        List<FlowPane> paneList = new ArrayList<>();

        // 反向遍历列表
        for (int i = messageList.size() - 1; i >= 0; --i) {
            Long sendFromId = messageList.get(i).getSendFrom();

            FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml",
                    messageList.get(i), sendFromId.equals(self.getUserId()) ? self : friendHandler.getFriend());

            // 用户发的消息放到右边
            if (sendFromId.equals(self.getUserId())) {
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
        sessionHandler.onNewFriendMessageArrived(rawJson);
    }

    /**
     * 注册成为订阅者
     */
    @Override
    public void registerAsSubscriber() {
        sessionHandler.registerAsSubscriber();
    }
}

class FriendHandler implements ChattingBoardController.SessionHandler {

    private User friend;

    private ChattingBoardController controllerCallback;

    FriendHandler(User friend, ChattingBoardController controllerCallback) {
        this.controllerCallback = controllerCallback;
        this.friend = friend;
    }

    @Override
    public List<Message> getMessage() {
        return controllerCallback.messageService.queryRecentMessage(controllerCallback.self.getUserId(), friend.getUserId());
    }

    @Override
    public void sendMessage() {
        byte[] message = new byte[256];
        try {
            message = JsonHelper.generateByteMessage(1, controllerCallback.inputBox.getText(), UserState.getSingleton().getUser().getUserId(), friend.getUserId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        byte[] finalMessage = message;

        FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml", Message.parseToMessageEntity(message), UserState.getSingleton().getUser());
        fxmlLoaderUtil.getController().rightAlign();

        UpdateUiUtil.updateUi(() -> controllerCallback.messageBox.getChildren().add(fxmlLoaderUtil.getPane()));

        Main.getClientThreadPool().execute(() -> Main.getTcpClient().sendMessage(finalMessage));
        controllerCallback.inputBox.setText("");
    }

    /**
     * 新的好友消息到来
     *
     * @param rawJson 好友消息
     */
    @Override
    public void onNewFriendMessageArrived(JsonNode rawJson) {
        FlowPane flowPane = null;
        try {
            flowPane = FxmlLoaderUtil.<FlowPane, MessageCmpController>loadFxComponent("MessageCmp.fxml", rawJson.get("data"), friendHandler.getFriend());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FlowPane finalFlowPane = flowPane;
        UpdateUiUtil.updateUi(() -> controllerCallback.messageBox.getChildren().add(finalFlowPane));
    }

    @Override
    public void registerAsSubscriber() {
        SessionState.addToFriendChattingBoardSubscriberMap(friend.getUserId(), controllerCallback);
    }
}

class GroupHandler implements ChattingBoardController.SessionHandler {
    private Group group;

    private ChattingBoardController controllerCallback;

    GroupHandler(Group group, ChattingBoardController controllerCallback) {
        this.controllerCallback = controllerCallback;
        this.group = group;
    }

    @Override
    public List<Message> getMessage() {
        return controllerCallback.messageService.queryGroupRecentMessage(group.getGroupId());
    }

    @Override
    public void sendMessage() {

    }

    /**
     * 新的好友消息到来
     *
     * @param rawJson 好友消息
     */
    @Override
    public void onNewFriendMessageArrived(JsonNode rawJson) {

    }

    @Override
    public void registerAsSubscriber() {
        SessionState.addToGroupChattingBoardSubscriberMap(group.getGroupId(), controllerCallback);
    }
}
