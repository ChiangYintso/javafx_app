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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.components.MessageCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.common.javafx.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.common.javafx.SceneRouter;
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
    private Button emojiBtn;

    @FXML
    private Button emoji0;

    @FXML
    private Button emoji1;

    @FXML
    private Button emoji2;

    @FXML
    private Button emoji3;

    @FXML
    private Button emoji4;

    @FXML
    private Button emoji5;

    @FXML
    private Button emoji6;

    @FXML
    private Button emoji7;

    @FXML
    private Button emoji8;

    @FXML
    private Button emoji9;

    @FXML
    private Button emoji10;

    @FXML
    private Button emoji11;

    @FXML
    private Button emoji12;

    @FXML
    private Button emoji13;

    @FXML
    private Button emoji14;

    @FXML
    private Button emoji15;

    @FXML
    private Button emoji16;

    @FXML
    private Button emoji17;

    @FXML
    private Button emoji18;

    @FXML
    private Button emoji19;

    @FXML
    private Button emoji20;

    @FXML
    private Button emoji21;

    @FXML
    private Button emoji22;

    @FXML
    private Button emoji23;

    @FXML
    private Button emoji24;

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

    @FXML
    private GridPane emojiPane;

    SessionCardCmpController.Session session;

    User self = null;

    MessageService messageService = new MessageServiceImpl();

    private AbstractSessionHandler sessionHandler = null;

    abstract static class AbstractSessionHandler implements SessionState.Subscriber {

        protected ChattingBoardController controllerCallBack;

        abstract List<Message> getMessage();

        AbstractSessionHandler(ChattingBoardController controllerCallBack, SessionCardCmpController.Session session) {
            this.controllerCallBack = controllerCallBack;
            controllerCallBack.session = session;
        }

        Long getSessionId() {
            return controllerCallBack.session.getId();
        }

        // ������Ϣ, ��ΪȺ��, sendToΪȺ��ID; ��Ϊ��������, sendToΪ����ID
        void sendMessage() {
            byte[] message = new byte[256];
            try {
                Long sendFrom = UserState.getSingleton().getUser().getUserId();
                // ����������Ϣ���ϴ����ݿ�
                message = JsonHelper.generateByteMessage(getMessageType(),
                        Message.filterSensitiveWords(sendFrom, controllerCallBack.inputBox.getText(), true),
                        sendFrom, getSessionId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            byte[] finalMessage = message;

            FxmlCmpLoaderUtil<FlowPane, MessageCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil<>("client", "MessageCmp.fxml", Message.parseToMessageEntity(message), UserState.getSingleton().getUser());
            fxmlCmpLoaderUtil.getController().rightAlign();

            UpdateUiUtil.updateUi(() -> controllerCallBack.messageBox.getChildren().add(fxmlCmpLoaderUtil.getPane()));

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
        public void onNewMessageArrived(JsonNode rawJson) {
            FlowPane flowPane = null;
            try {
                flowPane = FxmlCmpLoaderUtil.<FlowPane, MessageCmpController>loadFxComponent("client", "MessageCmp.fxml", rawJson.get("data"), controllerCallBack.session);
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

    void updateChattingBoardInfo() {
        UpdateUiUtil.updateUi(() -> {
            avatar.setImage(new Image(session.getAvatar()));
            sessionNameField.setText(session.getSessionName());
        });
    }

    @FXML
    void showChattingRecord(ActionEvent event) {
        SceneRouter.showTempStage("�����¼", "ChattingRecordBoard.fxml", "client");
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

        updateChattingBoardInfo();
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

            FxmlCmpLoaderUtil<FlowPane, MessageCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil<>("client", "MessageCmp.fxml",
                    messageList.get(i), sendFromId.equals(self.getUserId()) ? self : sessionHandler.getSendFrom(messageList.get(i).getSendFrom()));

            // �û�������Ϣ�ŵ��ұ�
            if (sendFromId.equals(self.getUserId())) {
                fxmlCmpLoaderUtil.getController().rightAlign();
            }

            paneList.add(fxmlCmpLoaderUtil.getPane());
        }
        UpdateUiUtil.updateUi(() -> messageBox.getChildren().addAll(paneList));

        messageBox.heightProperty().addListener(e -> scrollPane.setVvalue(1));
    }

    @FXML
    void showOption(ActionEvent event) {
        SessionState.setSelectedSession(session);
        SceneRouter.showTempStage("��������", "FriendIntroBoard.fxml", "client");
    }

    @FXML
    void selectEmoji(ActionEvent event) {
        inputBox.appendText(((Button)event.getSource()).getText());
    }

    @FXML
    void showEmojiPane(ActionEvent event) {
        emojiPane.setVisible(!emojiPane.isVisible());
    }

    /**
     * �µĺ�����Ϣ����
     *
     * @param rawJson ������Ϣ
     */
    @Override
    public void onNewMessageArrived(JsonNode rawJson) {
        sessionHandler.onNewMessageArrived(rawJson);
    }

    /**
     * ���ѻ�Ⱥ�ĵ�״̬�����ı�
     *
     * @param rawJson ԭʼJSON����
     */
    @Override
    public void onStatusChanged(JsonNode rawJson) {
        sessionHandler.onStatusChanged(rawJson);
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
        return (User) controllerCallBack.session;
    }

    @Override
    public Integer getMessageType() {
        // TODO ��Ϣ����
        return 1;
    }

    /**
     * ���ѵ�״̬�����ı�
     *
     * @param rawJson ԭʼJSON����
     */
    @Override
    public void onStatusChanged(JsonNode rawJson) {
        controllerCallBack.session = JsonHelper.getStatusChangedUser(rawJson);
        controllerCallBack.updateChattingBoardInfo();
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
        return ((Group)controllerCallBack.session).getMessageSendFrom(sendFromId);
    }

    @Override
    public Integer getMessageType() {
        // TODO ��Ϣ����
        return 11;
    }

    /**
     * ���ѻ�Ⱥ�ĵ�״̬�����ı�
     *
     * @param rawJson ԭʼJSON����
     */
    @Override
    public void onStatusChanged(JsonNode rawJson) {
        // TODO ��ȡȺ����Ϣ
        controllerCallBack.updateChattingBoardInfo();
    }

    @Override
    public void registerAsSubscriber() {
        SessionState.addToGroupChattingBoardSubscriberMap(getSessionId(), controllerCallBack);
    }
}
