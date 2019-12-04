package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.client.state.UserState;

/**
 * @author Jiang Yinzuo
 */
public class UserSettingBoardController {

	@FXML
	private ImageView avator;
	
	@FXML
	private TextField userNameField;

	@FXML
	public void initialize() {
		User user = UserState.getSingleton().getUser();
		userNameField.setText(user.getUserName());
//		avator.setImage(new Image(user.getAvatarUrl()));
	}
}
