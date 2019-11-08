package main.java.pers.jiangyinzuo.chat.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.pers.jiangyinzuo.chat.ui.javafx.router.SceneRouter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    	new SceneRouter().showStage(primaryStage, "ÍøÂçÁÄÌìÊÒ", "login.fxml");
    	new SceneRouter().showStage(new Stage(), "ÍøÂçÁÄÌìÊÒ", "register.fxml");
    }
    
    void main(String[] args) {
        launch();
    }

}