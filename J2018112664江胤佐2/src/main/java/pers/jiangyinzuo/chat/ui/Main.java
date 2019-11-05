package main.java.pers.jiangyinzuo.chat.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.pers.jiangyinzuo.chat.ui.scenes.AbstractScene;
import main.java.pers.jiangyinzuo.chat.ui.scenes.IndexScene;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        AbstractScene sceneBean = new IndexScene("index.fxml");
        stage.setScene(sceneBean.getScene());
        stage.setTitle("Õ¯¬Á¡ƒÃÏ “");
        stage.show();
    }

    public static 
    void main(String[] args) {
        launch();
    }

}