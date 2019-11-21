package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.entity.Session;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;
import main.java.pers.jiangyinzuo.chat.ui.state.ChattingBoardInfo;

public class ChattingBoardController {

    @FXML
    private Button sendBtn;

    @FXML
    private Button option;

    @FXML
    private Text sessionNameField;

    @FXML
    private ImageView avatar;
    
    private Session session;

    @FXML
    void sendMessage(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	session = ChattingBoardInfo.getSession();
    	avatar.setImage(new Image(session.getAvatarUrl()));
    	sessionNameField.setText(session.getSessionName());
    }
    
    @FXML
    void showOption(ActionEvent event) {
    	ChattingBoardInfo.setSession(session);
    	SceneRouter.showTempStage("∫√”—œÍ«È", "FriendIntroBoard.fxml");
    }

}
