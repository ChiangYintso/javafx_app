package pers.jiangyinzuo.chat.server.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.common.javafx.StageManager;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StageManager.addStage(stage, "管理端登录");
        StageManager.showStage("管理端登录", "Login.fxml", "server");
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
