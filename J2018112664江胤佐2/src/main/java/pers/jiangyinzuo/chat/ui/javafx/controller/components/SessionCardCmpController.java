package main.java.pers.jiangyinzuo.chat.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
}
