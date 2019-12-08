package pers.jiangyinzuo.chat.client.javafx.controller.components;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;

/**
 * @author Jiang Yinzuo
 */
public class SessionCardCmpController implements SessionState.Subscriber {
    @FXML
    private Circle bubble;

    @FXML
    private Pane sessionCardPane;

    @FXML
    private ImageView avatar;

    @FXML
    private Text statusText;

    @FXML
    private Text name;

    private Session session;

    /**
     * �µĺ�����Ϣ����
     *
     * @param rawJson ������Ϣ
     */
    @Override
    public void onNewMessageArrived(JsonNode rawJson) {
        UpdateUiUtil.updateUi(() -> bubble.setVisible(true));
    }

    /**
     * ���ѻ�Ⱥ�ĵ�״̬�����ı�
     *
     * @param rawJson ԭʼJSON����
     */
    @Override
    public void onStatusChanged(JsonNode rawJson) {
        // TODO Ⱥ��״̬�仯
        UpdateUiUtil.updateUi(() -> init(JsonHelper.getStatusChangedUser(rawJson)));
    }

    /**
     * ��ʼ��Controllerʱע���Ϊ������
     */
    @Override
    public void registerAsSubscriber() {
        if (session instanceof User) {
            SessionState.addToFriendSessionCardCmpDistributorMap(session.getId(), this);
        } else if (session instanceof Group) {
            SessionState.addToGroupSessionCardCmpDistributorMap(session.getId(), this);
        } else {
            throw new RuntimeException();
        }
    }

    public interface Publisher {
        /**
         * ���º����������
         * �յ���JSON:
         * {
         *     "option": Option.FRIENDS_ONLINE_STATUS,
         *     "sendTo": <���͸��ͻ��˵�id>,
         *     "onLineList": <���ߵĺ���id>
         * }
         * @param jsonNode
         */
        void onUpdateFriendOnlineStatus(JsonNode jsonNode);
    }

    /**
     * ������ʱ��ʾ��������
     */
    public void changeOnlineStatus() {
        UpdateUiUtil.updateUi(() -> this.statusText.setText("����"));
    }

    public interface Session {
        /**
         * ��ȡ�Ự����
         *
         * @return ��������Ⱥ����
         */
        String getSessionName();

        /**
         * ��ȡͷ��
         *
         * @return ���ѻ�Ⱥ��ͷ���URL
         */
        String getAvatar();

        Long getId();

        String getStatus();
    }

    public <T extends Session> void init(T session) {
        name.setText(session.getSessionName());
        avatar.setImage(new Image(session.getAvatar()));
        statusText.setText(session.getStatus());
        this.session = session;
        this.registerAsSubscriber();
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        sessionCardPane.setStyle(sessionCardPane.getStyle() + ";-fx-border-color: red");
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        sessionCardPane.setStyle(sessionCardPane.getStyle() + ";-fx-border-color: none");
    }

    @FXML
    void onClickedCard(MouseEvent event) {
        SessionState.setSelectedSession(session);
        SceneRouter.showTempStage("�Ự����", "ChattingBoard.fxml");
        this.bubble.setVisible(false);
    }
}
