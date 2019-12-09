package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class FriendInfoBoardController {

    @FXML
    private Text friendName;

    @FXML
    private Text friendId;

    @FXML
    private ImageView friendAvatar;

    @FXML
    private Button deleteFriendBtn;

    @FXML
    private Text friendIntro;

    @FXML
    private TextField friendCategory;

    @FXML
    private Button editFriendCategoryBtn;

    private User friend;

    private FriendService friendService = new FriendServiceImpl();

    private SessionCardCmpController.Session session;

    @FXML
    void deleteFriend(ActionEvent event) {
        friendService.deleteFriend(friend.getUserId());
        ControllerProxy.getMainBoardController().loadTreeView();
        StageManager.friendDeleted(friend.getUserId());
        deleteFriendBtn.getScene().getWindow().hide();
    }

    @FXML
    void editFriendCategory(ActionEvent event) {
        if (friendCategory.getText() == null || friendCategory.getText().isBlank()) {
            CustomAlertBoard.showAlert("分组不能为空");
        }
        friendService.updateFriendCategory(session.getId(), friendCategory.getText());
        ControllerProxy.getMainBoardController().loadTreeView();
    }

    @FXML
    void initialize() {
        session = SessionState.getSelectedSession();
        StageManager.friendInfoBoardStageMap.put(session.getId(), StageManager.getCurrentStage());

        friend = (User) SessionState.getSelectedSession();
        friendId.setText(friend.getUserId().toString());
        friendAvatar.setImage(new Image(friend.getAvatar()));
        friendCategory.setText(friend.getFriendCategory());
        friendIntro.setText(friend.getIntro());
        friendName.setText(friend.getUserName());
    }
}
