package pers.jiangyinzuo.chat.client.javafx;

import java.util.concurrent.*;

import javafx.application.Application;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.TcpClient;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.domain.repository.MessageRepo;

import static pers.jiangyinzuo.chat.client.state.SensitiveWordsState.loadSensitiveWords;

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
            MessageRepo.shutDownThreadPool();
            clientThreadPool.shutdown();
            tcpClient.exit();
            isOn = false;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // ¼ÓÔØÃô¸Ð´Ê
        loadSensitiveWords();

        StageManager.addStage(primaryStage, "µÇÂ¼");
        StageManager.showStage("µÇÂ¼", "Login.fxml", "client");
    }

    public static void main(String[] args) {
        try {
            launch();
        } finally {
            exit();
        }
    }

}