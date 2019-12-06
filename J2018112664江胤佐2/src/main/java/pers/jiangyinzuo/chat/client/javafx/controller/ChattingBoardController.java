package pers.jiangyinzuo.chat.client.javafx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;

/**
 * @author Jiang Yinzuo
 */
public class ChattingBoardController {

    @FXML
    private TextArea inputBox;

    @FXML
    private Button sendBtn;

    @FXML
    private Button option;

    @FXML
    private Text sessionNameField;

    @FXML
    private ImageView avatar;
    
    private SessionCardCmpController.Session session;

    private User user = null;

    private Group group = null;

    @FXML
    void sendMessage(ActionEvent event) {
        byte[] message = new byte[256];
        if (user != null) {
            try {
                message = JsonHelper.generateByteMessage(1, inputBox.getText(), UserState.getSingleton().getUser().getUserId(), user.getUserId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        byte[] finalMessage = message;
        Main.getClientThreadPool().execute(() -> Main.getTcpClient().sendMessage(finalMessage));
        inputBox.setText("");
    }

    @FXML
    void initialize() {
    	session = SessionState.getSelectedSession();
    	if (session instanceof User) {
    	    user = (User) session;
        } else if (session instanceof Group) {
    	    group = (Group) session;
        }
    	avatar.setImage(new Image(session.getAvatar()));
    	sessionNameField.setText(session.getName());
    }
    
    @FXML
    void showOption(ActionEvent event) {
        SessionState.setSelectedSession(session);
    	SceneRouter.showTempStage("∫√”—œÍ«È", "FriendIntroBoard.fxml");
    }

}
