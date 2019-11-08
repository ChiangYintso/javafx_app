package main.java.pers.jiangyinzuo.chat.ui.javafx.router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneRouter {
	private static Map<String, Scene> sceneMap = new HashMap<>();
	
	public Scene getScene(String sceneName) throws IOException {
		if (sceneMap.get(sceneName) == null) {
			createScene(sceneName);
		}
		return sceneMap.get(sceneName);
	}
	
	public void createScene(String sceneName) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../scenes/" + sceneName));
		sceneMap.put(sceneName, new Scene(root));
	}
	
	public void destructScene(String sceneName) {
		sceneMap.remove(sceneName);
	}
	
	public void showStage(Stage stage, String title, String sceneName) throws IOException {
		stage.setScene(getScene(sceneName));
		stage.setTitle(title);
		stage.show();
	}
	
}
