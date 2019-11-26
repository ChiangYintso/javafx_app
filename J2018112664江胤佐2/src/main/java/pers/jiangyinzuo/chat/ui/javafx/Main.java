package pers.jiangyinzuo.chat.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	SceneRouter.addStage(primaryStage, "��¼");
    	SceneRouter.showStage("��¼", "Login.fxml");
    }
    
    void main(String[] args) {
        launch();
    }

}