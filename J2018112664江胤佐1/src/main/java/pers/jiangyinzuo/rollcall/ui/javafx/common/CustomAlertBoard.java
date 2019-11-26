package pers.jiangyinzuo.rollcall.ui.javafx.common;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CustomAlertBoard {
	public static void showAlert(String infomation) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(infomation);
		alert.showAndWait();
	}
}
