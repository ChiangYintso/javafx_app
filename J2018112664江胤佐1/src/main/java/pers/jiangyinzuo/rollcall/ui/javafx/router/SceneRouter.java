package pers.jiangyinzuo.rollcall.ui.javafx.router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class SceneRouter {

	// 存放页面的容器
	private static Map<String, Scene> sceneMap = new HashMap<>();

	// 存放舞台的容器
	private static Map<String, Stage> stageMap = new HashMap<>();

	private static Scene getScene(String sceneName) throws IOException {
		if (sceneMap.get(sceneName) == null) {
			createScene(sceneName);
		}
		return sceneMap.get(sceneName);
	}

	private static void createScene(String sceneName) throws IOException {
		for (int i = 0; i < sceneName.length(); ++i) {
			if (sceneName.charAt(i) == '.') {
				Parent root = FXMLLoader.load(SceneRouter.class.getResource("../scenes/" + sceneName));
				sceneMap.put(sceneName, new Scene(root));
			}
		}
	}

	public static void showStage(String stageName, String sceneName) throws IOException {
		Stage stage = getStage(stageName);
		stage.setScene(getScene(sceneName));
		stage.setTitle(stageName);
		
		stage.show();
	}
	
	public static void showTempStage(String stageTitle, String fxmlFileName) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(SceneRouter.class.getResource("../scenes/" + fxmlFileName));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(stageTitle);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Stage getStage(String stageName) {
		Stage stage = stageMap.get(stageName);
		if (stage == null) {
			stage = new Stage();
			addStage(stage, stageName);
		}
		return stage;
	}

	public static void addStage(Stage stage, String stageName) {
		stageMap.put(stageName, stage);
	}

	public static void closeStage(String stageName) {
		if (stageMap.get(stageName) != null) {
			stageMap.get(stageName).close();
		}
	}
}
