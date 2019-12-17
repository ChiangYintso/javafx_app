package pers.jiangyinzuo.chat.common.javafx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 舞台管理类
 *
 * @author Jiang Yinzuo
 */
public class StageManager {

    public static Map<Long, Stage> friendChattingBoardStageMap = new HashMap<>();
    public static Map<Long, Stage> friendChattingRecordBoardStageMap = new HashMap<>();
    public static Map<Long, Stage> groupChattingBoardStageMap = new HashMap<>();
    public static Map<Long, Stage> groupChattingRecordBoardStageMap = new HashMap<>();

    /**
     * 删除好友后关闭Stage
     * @param friendId 好友ID
     */
    public static void friendDeleted(Long friendId) {
        onSessionDeleted(friendId, friendChattingRecordBoardStageMap, friendChattingBoardStageMap);
    }

    public static void groupDeleted(Long groupId) {
        onSessionDeleted(groupId, groupChattingRecordBoardStageMap, groupChattingBoardStageMap);
    }

    public static void onSessionDeleted(Long groupId, Map<Long, Stage> chattingRecordBoardStageMap, Map<Long, Stage> chattingBoardStageMap) {
        if (chattingRecordBoardStageMap.get(groupId) != null) {
            chattingRecordBoardStageMap.get(groupId).close();
            chattingRecordBoardStageMap.remove(groupId);
        }
        if (chattingBoardStageMap.get(groupId) != null) {
            chattingBoardStageMap.get(groupId).close();
            chattingBoardStageMap.remove(groupId);
        }
    }

    // 存放页面的容器
    private static Map<String, Scene> sceneMap = new HashMap<>();

    // 存放舞台的容器
    private static Map<String, Stage> stageMap = new HashMap<>();

    private static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    private static Scene getScene(String sceneName, String path) {
        if (sceneMap.get(sceneName) == null) {
            createScene(sceneName, path);
        }
        return sceneMap.get(sceneName);
    }

    private static void createScene(String sceneName, String path) {
        for (int i = 0; i < sceneName.length(); ++i) {
            if (sceneName.charAt(i) == '.') {
                Parent root = null;
                try {
                    root = FXMLLoader.load(StageManager.class.getResource("../../" + path + "/javafx/scenes/" + sceneName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sceneMap.put(sceneName, new Scene(root));
            }
        }
    }

    public static void showStage(String stageName, String sceneName, String path) {
        Stage stage = getStage(stageName);
        stage.setScene(getScene(sceneName, path));
        stage.setTitle(stageName);
        currentStage = stage;
        stage.setResizable(false);
        stage.show();
    }

    public static void showTempStage(String stageTitle, String fxmlFileName, String path) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(StageManager.class.getResource("../../" + path + "/javafx/scenes/" + fxmlFileName));
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

    public static Stage getStage(String stageName) {
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
