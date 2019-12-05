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

    private static ExecutorService clientThreadPool = new ThreadPoolExecutor(20, 25,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5),
            Executors.defaultThreadFactory());

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

    public static boolean isOn = true;

    public static void exit() {
        if (isOn) {
            clientThreadPool.shutdown();
            tcpClient.exit();
            isOn = false;
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneRouter.addStage(primaryStage, "��¼");
        SceneRouter.showStage("��¼", "Login.fxml");
    }

    void main(String[] args) {
        try {
            launch();
        } finally {
            exit();
        }
    }

}