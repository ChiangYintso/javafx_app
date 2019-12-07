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

    private AbstractSessionHandler sessionHandler = null;

    abstract static class AbstractSessionHandler implements SessionState.Subscriber {

        protected ChattingBoardController controllerCallBack;

        protected SessionCardCmpController.Session session;

        abstract List<Message> getMessage();

        AbstractSessionHandler(ChattingBoardController controllerCallBack, SessionCardCmpController.Session session) {
            this.controllerCallBack = controllerCallBack;
            this.session = session;
        }

        Long getSessionId() {
            return session.getId();
        }

        // ������Ϣ, ��ΪȺ��, sendToΪȺ��ID; ��Ϊ��������, sendToΪ����ID
        void sendMessage() {
            byte[] message = new byte[256];
            try {
                message = JsonHelper.generateByteMessage(getMessageType(), controllerCallBack.inputBox.getText(), UserState.getSingleton().getUser().getUserId(), getSessionId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            byte[] finalMessage = message;

            FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml", Message.parseToMessageEntity(message), UserState.getSingleton().getUser());
            fxmlLoaderUtil.getController().rightAlign();

            UpdateUiUtil.updateUi(() -> controllerCallBack.messageBox.getChildren().add(fxmlLoaderUtil.getPane()));

            Main.getClientThreadPool().execute(() -> Main.getTcpClient().sendMessage(finalMessage));
            controllerCallBack.inputBox.setText("");
        }

        /**
         * ��ȡ������Ϣ��Userʵ����
         * @return
         */
        abstract User getSendFrom(Long sendFromId);

        abstract Integer getMessageType();

        @Override
        public void onNewFriendMessageArrived(JsonNode rawJson) {
            FlowPane flowPane = null;
            try {
                flowPane = FxmlLoaderUtil.<FlowPane, MessageCmpController>loadFxComponent("MessageCmp.fxml", rawJson.get("data"), session);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FlowPane finalFlowPane = flowPane;
            UpdateUiUtil.updateUi(() -> controllerCallBack.messageBox.getChildren().add(finalFlowPane));
        }
    }

    @FXML
    void sendMessage(ActionEvent event) {
        sessionHandler.sendMessage();
    }

    @FXML
    void initialize() {
        session = SessionState.getSelectedSession();

        // ע��SessionHandler
        if (session instanceof User) {
            sessionHandler = new FriendHandler(this, (User) session);
        } else if (session instanceof Group) {
            sessionHandler = new GroupHandler(this, (Group) session);
        } else {
            throw new RuntimeException();
        }

        avatar.setImage(new Image(session.getAvatar()));
        sessionNameField.setText(session.getSessionName());
        this.registerAsSubscriber();
        this.messageBox.setMaxHeight(450);

        // ��ǰ�ͻ����û�
        this.self = UserState.getSingleton().getUser();

        // ��ѯ���ݿ��ȡ�����¼
        List<Message> messageList = sessionHandler.getMessage();

        List<FlowPane> paneList = new ArrayList<>();

        // ��������б�
        for (int i = messageList.size() - 1; i >= 0; --i) {
            Long sendFromId = messageList.get(i).getSendFrom();

            FxmlLoaderUtil<FlowPane, MessageCmpController> fxmlLoaderUtil = new FxmlLoaderUtil<>("MessageCmp.fxml",
                    messageList.get(i), sendFromId.equals(self.getUserId()) ? self : sessionHandler.getSendFrom(messageList.get(i).getSendFrom()));

            // �û�������Ϣ�ŵ��ұ�
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
        SceneRouter.showTempStage("��������", "FriendIntroBoard.fxml");
    }

    /**
     * �µĺ�����Ϣ����
     *
     * @param rawJson ������Ϣ
     */
    @Override
    public void onNewFriendMessageArrived(JsonNode rawJson) {
        sessionHandler.onNewFriendMessageArrived(rawJson);
    }

    /**
     * ע���Ϊ������
     */
    @Override
    public void registerAsSubscriber() {
        sessionHandler.registerAsSubscriber();
    }
}

class FriendHandler extends ChattingBoardController.AbstractSessionHandler {

    FriendHandler(ChattingBoardController controllerCallBack, SessionCardCmpController.Session session) {
        super(controllerCallBack, session);
    }

    @Override
    public List<Message> getMessage() {
        return controllerCallBack.messageService.queryRecentMessage(controllerCallBack.self.getUserId(), getSessionId());
    }

    /**
     * ��ȡ������Ϣ��Userʵ����
     *
     * @param sendFromId
     * @return
     */
    @Override
    public User getSendFrom(Long sendFromId) {
        return (User) session;
    }

    @Override
    public Integer getMessageType() {
        // TODO ��Ϣ����
        return 1;
    }

    @Override
    public void registerAsSubscriber() {
        SessionState.addToFriendChattingBoardSubscriberMap(getSessionId(), controllerCallBack);
    }
}

class GroupHandler extends ChattingBoardController.AbstractSessionHandler {

    GroupHandler(ChattingBoardController controllerCallBack, SessionCardCmpController.Session session) {
        super(controllerCallBack, session);
    }

    @Override
    public List<Message> getMessage() {
        return controllerCallBack.messageService.queryGroupRecentMessage(getSessionId());
    }

    /**
     * ��ȡ������Ϣ��Userʵ����
     *
     * @param sendFromId
     * @return Userʵ����; ���ó�Ա����Ⱥ��, ����null
     */
    @Override
    public User getSendFrom(Long sendFromId) {
        return ((Group)session).getMessageSendFrom(sendFromId);
    }

    @Override
    public Integer getMessageType() {
        // TODO ��Ϣ����
        return 11;
    }

    @Override
    public void registerAsSubscriber() {
        SessionState.addToGroupChattingBoardSubscriberMap(getSessionId(), controllerCallBack);
    }
}
