package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public class FriendIntroBoardController {

    @FXML
    private Text friendName;

    @FXML
    private Text friendId;

    @FXML
    private ImageView friendAvatar;

    @FXML
    private Button deleteFriend;

    @FXML
    private Text friendIntro;

    @FXML
    private TextField friendCategory;

    @FXML
    private Button editFriendCategoryBtn;

    private User friend;

    @FXML
    void deleteFriendBtn(ActionEvent event) {

    }

    @FXML
    void editFriendCategory(ActionEvent event) {

    }

    @FXML
    void initialize() {
        friend = (User) SessionState.getSelectedSession();
        friendId.setText(friend.getUserId().toString());
        friendAvatar.setImage(new Image(friend.getAvatar()));
        friendCategory.setText(friend.getFriendCategory());
        friendIntro.setText(friend.getIntro());
        friendName.setText(friend.getUserName());
    }
}
