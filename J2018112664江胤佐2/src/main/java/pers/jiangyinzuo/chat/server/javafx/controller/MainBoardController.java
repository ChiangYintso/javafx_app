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
        SceneRouter.showTempStage("�����¼�������", "ChattingRecordManagementBoard.fxml", "server");
    }

    @FXML
    void switchServer(ActionEvent event) {
        if (tcpServer.isServerOn()) {
            tcpServer.exitServer();
            serverSwitchBtn.setText("�򿪷�����");
        } else {
            tcpServer.runServer();
            serverSwitchBtn.setText("�رշ�����");
        }
    }

    @FXML
    void initialize() {
        mainStage = SceneRouter.getStage("����������");
        mainStage.setOnCloseRequest((e) -> {
            if (tcpServer.isServerOn()) {
                tcpServer.exitServer();
            }
            AppIsOn = false;
            threadPool.shutdown();
            System.out.println("����˹ر�");
        });


        threadPool.execute(() -> {
            do {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (tcpServer.isServerOn()) {
                    totalCountText.setText("ȫ����������: " + tcpServer.getTotalOnlineCount());
                }
            } while (AppIsOn);
        });
    }

    @FXML
    void editSensitiveWords(ActionEvent event) {
        SceneRouter.showTempStage("��������дʹ���", "SensitiveWordsManager.fxml", "server");
    }
}
