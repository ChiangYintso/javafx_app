package pers.jiangyinzuo.chat.client.javafx;

import java.io.IOException;
import java.util.concurrent.*;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.TcpClient;
import pers.jiangyinzuo.chat.client.javafx.router.SceneRouter;

/**
 * @author Jiang Yinzuo
 */
public class Main extends Application {

    private static ExecutorService clientThreadPool = new ThreadPoolExecutor(3, 10, 30L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    private static TcpClient tcpClient;

    public static TcpClient getTcpClient() {
        return tcpClient;
    }

    public static void startTcpClient(Long userId) {
        tcpClient = new TcpClient(userId);
        tcpClient.run();
    }

    public static ExecutorService getClientThreadPool() {
        return clientThreadPool;
    }

    private static boolean isOn = true;

    public static void exit() {
        if (isOn) {
            clientThreadPool.shutdown();
            tcpClient.exit();
            isOn = false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneRouter.addStage(primaryStage, "µÇÂ¼");
        SceneRouter.showStage("µÇÂ¼", "Login.fxml");
    }

    void main(String[] args) {
        try {
            launch();
        } finally {
            exit();
        }
    }

}