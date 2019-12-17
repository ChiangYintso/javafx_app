package pers.jiangyinzuo.chat.client.javafx.controller.components;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
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
     * 新的好友消息到来
     *
     * @param rawJson 好友消息
     */
    @Override
    public void onNewMessageArrived(JsonNode rawJson) {
        UpdateUiUtil.updateUi(() -> bubble.setVisible(true));
    }

    /**
     * 好友或群聊的状态发生改变
     *
     * @param rawJson 原始JSON数据
     */
    @Override
    public void onStatusChanged(JsonNode rawJson) {
        // TODO 群聊状态变化
        UpdateUiUtil.updateUi(() -> init(JsonHelper.getStatusChangedUser(rawJson)));
    }

    /**
     * 初始化Controller时注册成为订阅者
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

    public void changeBlockStatus() {
        UpdateUiUtil.updateUi(() -> {
            if (session instanceof Group) {
                session.changeBlockStat();
                if (session.isBlocked()) {
                    statusText.setVisible(true);
                    statusText.setText("封禁中");
                } else {
                    statusText.setVisible(false);
                }
            }
        });
    }

    public interface Publisher {
        /**
         * 更新好友上线情况
         * 收到的JSON:
         * {
         *     "option": Option.FRIENDS_ONLINE_STATUS,
         *     "sendTo": <发送给客户端的id>,
         *     "onLineList": <上线的好友id>
         * }
         * @param jsonNode
         */
        void onUpdateFriendOnlineStatus(JsonNode jsonNode);
    }

    /**
     * 刚上线时显示好友在线
     */
    public void changeOnlineStatus() {
        UpdateUiUtil.updateUi(() -> this.statusText.setText("在线"));
    }

    public interface Session {
        /**
         * 获取会话名称
         *
         * @return 好友名或群聊名
         */
        String getSessionName();

        /**
         * 获取头像
         *
         * @return 好友或群聊头像的URL
         */
        String getAvatar();

        Long getId();

        String getStatus();

        Boolean isBlocked();

        void changeBlockStat();

        Session copy();
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
        sessionCardPane.setStyle(sessionCardPane.getStyle() + ";-fx-border-color: #626262;-fx-cursor: hand");
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        sessionCardPane.setStyle(sessionCardPane.getStyle() + ";-fx-border-color: none");
    }

    @FXML
    void onClickedCard(MouseEvent event) {
        SessionState.setSelectedSession(session);
        StageManager.showTempStage("会话窗口", "ChattingBoard.fxml", "client");
        this.bubble.setVisible(false);
        if (session instanceof Group) {
            StageManager.groupChattingBoardStageMap.put(session.getId(), StageManager.getCurrentStage());
        }
    }
}
