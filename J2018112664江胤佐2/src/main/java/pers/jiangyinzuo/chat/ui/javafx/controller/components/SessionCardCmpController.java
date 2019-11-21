package main.java.pers.jiangyinzuo.chat.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.entity.Group;
import main.java.pers.jiangyinzuo.chat.entity.Session;
import main.java.pers.jiangyinzuo.chat.entity.User;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;
import main.java.pers.jiangyinzuo.chat.ui.state.ChattingBoardInfo;

public class SessionCardCmpController {
    @FXML
    private Pane sessionCardPane;
    
    @FXML
    private ImageView avator;

    @FXML
    private Text userName;
    
    private Session session;

    public void init(Session session) {
    	this.session = session;
    	this.userName.setText(session.getSessionName());
    	avator.setImage(new Image(session.getAvatarUrl()));
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
    void openChattingRoom(MouseEvent event) {
    	ChattingBoardInfo.setSession(session);
    	SceneRouter.showTempStage("»á»°´°¿Ú", "ChattingBoard.fxml");
    }
}
