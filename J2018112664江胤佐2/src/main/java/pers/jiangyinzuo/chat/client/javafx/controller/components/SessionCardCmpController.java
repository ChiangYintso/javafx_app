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

import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
public class SessionCardCmpController implements SessionState.Distributor {
    @FXML
    private Circle bubble;

    @FXML
    private Pane sessionCardPane;

    @FXML
    private ImageView avatar;

    @FXML
    private Text name;

    private Session session;

    /**
     * �µĺ�����Ϣ����
     *
     * @param jsonNode ������Ϣ
     */
    @Override
    public void onNewFriendMessageArrived(JsonNode jsonNode) {
        UpdateUiUtil.updateUi(() -> bubble.setVisible(true));
    }

    /**
     * ��ʼ��Controllerʱע���Ϊ������
     */
    @Override
    public void registerDistribution() {
        if (session instanceof User) {
            SessionState.addToFriendSessionCardCmpDistributorMap(session.getId(), this);
        } else if (session instanceof Group) {
            SessionState.addToGroupSessionCardCmpDistributorMap(session.getId(), this);
        } else {
            throw new RuntimeException();
        }
    }

    public interface Session {
        /**
         * ��ȡ�Ự����
         *
         * @return ��������Ⱥ����
         */
        String getName();

        /**
         * ��ȡͷ��
         *
         * @return ���ѻ�Ⱥ��ͷ���URL
         */
        String getAvatar();

        Long getId();
    }

    public <T extends Session> void init(T session) {
        name.setText(session.getName());
        avatar.setImage(new Image(session.getAvatar()));
        this.session = session;
        this.registerDistribution();
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

    public void onNewFriendMessageArrived() {

    }
}
