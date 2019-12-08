package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.common.javafx.SceneRouter;
import pers.jiangyinzuo.chat.server.TcpServer;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @author Jiang Yinzuo
 */
public class MainBoardController {

    @FXML
    private Text totalCountText;

    @FXML
    private Button serverSwitchBtn;

    @FXML
    private Button chattingRecordManagementBtn;

    @FXML
    private Button sensitiveWordBtn;

    private static final TcpServer tcpServer = new TcpServer(20000);

    private Stage mainStage;

    private ExecutorService threadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10));

    private boolean AppIsOn = true;

    @FXML
    void showChattingRecordManagementBoard(ActionEvent event) {
        SceneRouter.showTempStage("聊天记录管理面板", "ChattingRecordManagementBoard.fxml", "server");
    }

    @FXML
    void switchServer(ActionEvent event) {
        if (tcpServer.isServerOn()) {
            tcpServer.exitServer();
            serverSwitchBtn.setText("打开服务器");
        } else {
            tcpServer.runServer();
            serverSwitchBtn.setText("关闭服务器");
        }
    }

    @FXML
    void initialize() {
        mainStage = SceneRouter.getStage("管理端主面板");
        mainStage.setOnCloseRequest((e) -> {
            if (tcpServer.isServerOn()) {
                tcpServer.exitServer();
            }
            AppIsOn = false;
            threadPool.shutdown();
            System.out.println("管理端关闭");
        });


        threadPool.execute(() -> {
            do {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (tcpServer.isServerOn()) {
                    totalCountText.setText("全网在线人数: " + tcpServer.getTotalOnlineCount());
                }
            } while (AppIsOn);
        });
    }

    @FXML
    void editSensitiveWords(ActionEvent event) {
        SceneRouter.showTempStage("管理端敏感词管理", "SensitiveWordsManager.fxml", "server");
    }
}
