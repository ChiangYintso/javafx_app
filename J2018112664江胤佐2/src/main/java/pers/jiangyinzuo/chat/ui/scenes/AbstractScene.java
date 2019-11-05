package main.java.pers.jiangyinzuo.chat.ui.scenes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class AbstractScene {
	protected Scene scene;

	public Scene getScene() {
		return this.scene;
	}

	protected AbstractScene(String fxmlPath) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("./" + fxmlPath));
		this.scene = new Scene(root);
	}
}
