package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.client.state.ChattingBoardInfo;

public class ChattingBoardController {

    @FXML
    private Button sendBtn;

    @FXML
    private Button option;

    @FXML
    private Text sessionNameField;

    @FXML
    private ImageView avatar;
    
//    private Session session;

    @FXML
    void sendMessage(ActionEvent event) {

    }

    @FXML
    void initialize() {
//    	session = ChattingBoardInfo.getSession();
//    	avatar.setImage(new Image(session.getAvatarUrl()));
//    	sessionNameField.setText(session.getSessionName());
    }
    
    @FXML
    void showOption(ActionEvent event) {
//    	ChattingBoardInfo.setSession(session);
    	SceneRouter.showTempStage("∫√”—œÍ«È", "FriendIntroBoard.fxml");
    }

}
