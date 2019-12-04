package pers.jiangyinzuo.chat.client.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.TcpClient;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {

    private static TcpClient tcpClient;

    public static TcpClient getTcpClient() {
        return tcpClient;
    }

    public static void startTcpClient(Long userId) {
        tcpClient = new TcpClient(userId);
        tcpClient.run();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    	SceneRouter.addStage(primaryStage, "µÇÂ¼");
    	SceneRouter.showStage("µÇÂ¼", "Login.fxml");
    }
    
    void main(String[] args) {
        launch();
    }

}