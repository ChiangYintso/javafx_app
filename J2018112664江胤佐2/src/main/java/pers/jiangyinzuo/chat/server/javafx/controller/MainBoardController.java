package pers.jiangyinzuo.chat.server.javafx.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.common.javafx.SceneRouter;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.common.javafx.util.UpdateUiUtil;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.ForwardingMessageManager;
import pers.jiangyinzuo.chat.server.TcpServer;
import pers.jiangyinzuo.chat.server.javafx.GuiBroker;
import pers.jiangyinzuo.chat.server.javafx.controller.components.ToReviewGroupCmpController;

import java.io.IOException;
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
    private VBox toDoList;

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

        // ��Gui������ע��Controller����
        GuiBroker.setSingleMainBoardController(this);

        mainStage = SceneRouter.getStage("����������");
        mainStage.setOnCloseRequest((e) -> {
            // ֪ͨ������ת���������GUI�Ѿ��ر�
            ForwardingMessageManager.setServerGuiIsOn(false);
            if (tcpServer.isServerOn()) {
                tcpServer.exitServer();
            }
            AppIsOn = false;
            threadPool.shutdown();
            System.out.println("����˹ر�");

        });

        // ֪ͨ������ת���������GUI�ѿ���
        ForwardingMessageManager.setServerGuiIsOn(true);

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

    /**
     * ����µĴ�������
     * @param message
     * @param userId
     */
    public void addNewToDoItem(byte[] message, Long userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rawJson = null;
        try {
            rawJson = objectMapper.readTree(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert rawJson != null;
        String option = JsonHelper.getJsonOption(rawJson);
        AnchorPane pane = null;
        if (option.equals(JsonHelper.Option.FOUND_GROUP)) {
            FxmlCmpLoaderUtil<AnchorPane, ToReviewGroupCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil("server", "ToReviewGroupCmp.fxml", rawJson, userId.toString());
            pane = fxmlCmpLoaderUtil.getPane();
            fxmlCmpLoaderUtil.getController().setSelf(pane);
        }
        AnchorPane finalPane = pane;
        UpdateUiUtil.updateUi(() -> {
            toDoList.getChildren().add(finalPane);
        });

    }

    public void removeToDo(Parent pane) {
        this.toDoList.getChildren().remove(pane);
    }
}
