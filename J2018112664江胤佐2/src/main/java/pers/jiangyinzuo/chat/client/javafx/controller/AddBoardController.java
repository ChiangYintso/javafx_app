package pers.jiangyinzuo.chat.client.javafx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.TcpClient;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.FriendService;
import pers.jiangyinzuo.chat.service.impl.FriendServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static pers.jiangyinzuo.chat.helper.JsonHelper.Option.ADD_FRIEND;

/**
 * @author Jiang Yinzuo
 */
public class AddBoardController {

    private static final String FIND_FRIENDS = "查找好友";

    @FXML
    private TextField inputBox;

    @FXML
    private Button searchBtn;

    @FXML
    private RadioButton searchForFriendRadio;

    @FXML
    private RadioButton searchForGroupRadio;

    @FXML
    private ImageView avatarView;

    @FXML
    private Text idText;

    @FXML
    private Text userNameText;

    /**
     * 添加好友/群聊按钮
     */
    @FXML
    private Button addBtn;

    /**
     * 当前选中的radio
     */
    private String radioSelected = "查找好友";
    private User userSearched = null;
    private Group groupSearched = null;

    private FriendService friendService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        searchForFriendRadio.setToggleGroup(toggleGroup);
        searchForGroupRadio.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((changes, oldValue, newValue) -> {
            radioSelected = ((RadioButton) newValue).getText();
        });

        friendService = new FriendServiceImpl();
    }

    @FXML
    void search(ActionEvent event) {

        class VisibleHandler {
            void setVisible(boolean isVisible, User user) {
                addBtn.setVisible(isVisible);
                idText.setVisible(isVisible);
                userNameText.setVisible(isVisible);
                avatarView.setVisible(isVisible);
                userSearched = user;
                if (isVisible) {
                    idText.setText(user.getUserId().toString());
                    userNameText.setText(user.getUserName());
                    avatarView.setImage(new Image(user.getAvatar()));
                } else {
                    inputBox.setText("");
                }
            }
        }

        long id;
        try {
            id = Long.parseLong(inputBox.getText());
        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("ID格式错误");
            return;
        }
        if (FIND_FRIENDS.equals(this.radioSelected)) {
            User user = friendService.searchUser(id);
            if (user == null) {
                new VisibleHandler().setVisible(false, null);
                CustomAlertBoard.showAlert("未找到");
            } else {
                new VisibleHandler().setVisible(true, user);
            }
        }
    }

    @FXML
    void addFriend(ActionEvent event) {
        byte[] message = new byte[256];
        Map map = new HashMap<>(10);
        map.put("message", ADD_FRIEND);
        if (FIND_FRIENDS.equals(radioSelected)) {
            map.put("sendTo", userSearched.getUserId());
            map.put("sendFrom", UserState.getSingleton().getUser().getUserId());
            try {
                message = objectMapper.writeValueAsBytes(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            byte[] finalMessage = message;
            Main.getClientThreadPool().execute(() -> {
                Main.getTcpClient().sendMessage(finalMessage);
            });
        }

    }

    private class AddRequestHandler implements Runnable {

        AddRequestHandler() {

        }

        @Override
        public void run() {

        }
    }
}
