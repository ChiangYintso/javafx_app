package main.java.pers.jiangyinzuo.chat.ui.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.pers.jiangyinzuo.chat.entity.User;
import main.java.pers.jiangyinzuo.chat.ui.state.UserInfo;

public class UserSettingBoardController {

	@FXML
	private ImageView avator;
	
	@FXML
	private TextField userNameField;

	@FXML
	public void initialize() {
		User user = UserInfo.getSingleton().getUser();
		userNameField.setText(user.getUserName());
		avator.setImage(new Image(user.getAvatarUrl()));
	}
}
