package pers.jiangyinzuo.chat.server.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.common.javafx.SceneRouter;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneRouter.addStage(stage, "����˵�¼");
        SceneRouter.showStage("����˵�¼", "Login.fxml", "server");
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
