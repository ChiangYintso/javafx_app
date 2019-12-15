package pers.jiangyinzuo.rollcall.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	StageManager.addStage(primaryStage, "µÇÂ¼");
    	StageManager.showStage("µÇÂ¼", "Login.fxml");
    }
    
    void main(String[] args) {
        launch();
    }

}
