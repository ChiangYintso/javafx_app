package main.java.pers.jiangyinzuo.chat.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;

public class SessionCardCmpController {

    @FXML
    private ImageView avator;

    @FXML
    private Text userName;

    public void init(String imageUrl, String userName) {
    	this.userName.setText(userName);
    	Image image = new Image(imageUrl);
    	avator.setImage(image);
    }
    
    @FXML
    void openChattingRoom(MouseEvent event) {
    	SceneRouter.showTempStage("»á»°´°¿Ú", "ChattingBoard.fxml");
    }
}
