package pers.jiangyinzuo.rollcall.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	SceneRouter.addStage(primaryStage, "µÇÂ¼");
    	SceneRouter.showStage("µÇÂ¼", "Login.fxml");
    }
    
    void main(String[] args) {
        launch();
    }

}
