package pers.jiangyinzuo.rollcall.ui.javafx.router;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class StageManager {

	private static Stage currentStage;

	public static Stage getCurrentStage() {
		return currentStage;
	}

	// 存放页面的容器
	private static Map<String, Scene> sceneMap = new HashMap<>();

	// 存放舞台的容器
	private static Map<String, Stage> stageMap = new HashMap<>();

	private static Scene getScene(String sceneName) {
		if (sceneMap.get(sceneName) == null) {
			try {
				createScene(sceneName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sceneMap.get(sceneName);
	}

	private static void createScene(String sceneName) throws IOException {
		for (int i = 0; i < sceneName.length(); ++i) {
			if (sceneName.charAt(i) == '.') {
				Parent root = FXMLLoader.load(new URL("file:" + System.getProperty("user.dir") + "\\resources1\\" + sceneName));
				sceneMap.put(sceneName, new Scene(root));
			}
		}
	}

	public static void showStage(String stageName, String sceneName) {
		Stage stage = getStage(stageName);
		stage.setScene(getScene(sceneName));
		stage.setTitle(stageName);
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * 展示临时舞台
	 * @param stageTitle 舞台名称
	 * @param fxmlFileName FXML文件名
	 */
	public static void showTempStage(String stageTitle, String fxmlFileName) {
		try {
			Stage stage = new Stage();

			// 加载程序运行目录下的静态资源文件
			Parent root = FXMLLoader.load(
					new URL("file:" + System.getProperty("user.dir")
							+ "\\resources1\\" + fxmlFileName));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(stageTitle);
			stage.setResizable(false);
			stage.show();
			currentStage = stage;
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
