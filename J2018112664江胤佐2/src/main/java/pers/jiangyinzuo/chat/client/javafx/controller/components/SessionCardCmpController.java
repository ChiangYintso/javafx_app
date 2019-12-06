package pers.jiangyinzuo.chat.client.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;
import pers.jiangyinzuo.chat.client.state.ChattingBoardInfo;

/**
 * @author Jiang Yinzuo
 */
public class SessionCardCmpController {
    @FXML
    private Pane sessionCardPane;
    
    @FXML
    private ImageView avatar;

    @FXML
    private Text name;

    public interface Session {
        /**
         * 获取会话名称
         * @return 好友名或群聊名
         */
        String getName();

        /**
         * 获取头像
         * @return 好友或群聊头像的URL
         */
        String getAvatar();
    }

    public <T extends Session> void init(T session) {
        name.setText(session.getName());
        avatar.setImage(new Image(session.getAvatar()));
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
//    	ChattingBoardInfo.setSession(session);
    	SceneRouter.showTempStage("会话窗口", "ChattingBoard.fxml");
    }
}
