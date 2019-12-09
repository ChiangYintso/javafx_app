package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.server.javafx.controller.components.MessageRecordCmpController;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class ChattingRecordBoardController {

    @FXML
    private VBox messageBox;

    @FXML
    private Button nextBtn;

    @FXML
    private Button prevBtn;

    private MessageService messageService = new MessageServiceImpl();

    private int start = 1;

    private List<Message> messageList;

    private SessionCardCmpController.Session session;

    @FXML
    void onNext(ActionEvent event) {
        prevBtn.setDisable(false);
        start -= 5;
        if (start == 1) {
            nextBtn.setDisable(true);
            return;
        }
        queryMessageAndLoad();
    }

    @FXML
    void onPrev(ActionEvent event) {
        nextBtn.setDisable(false);
        start += 5;
        queryMessageAndLoad();
    }



    @FXML
    public void initialize() {
        session = SessionState.getSelectedSession();
        StageManager.friendChattingRecordBoardStageMap.put(session.getId(), StageManager.getCurrentStage());
        nextBtn.setDisable(true);
        queryMessageAndLoad();
    }

    private void queryMessageAndLoad() {
        messageList = messageService.queryUserMessageToFriends(
                UserState.getSingleton().getUser().getUserId(),
                session.getId(), start, 5,
                new Timestamp(System.currentTimeMillis()));

        if (messageList.size() == 0) {
            prevBtn.setDisable(true);
            return;
        }
        messageBox.getChildren().clear();
        for (int i = messageList.size() - 1; i >= 0; --i) {
            FxmlCmpLoaderUtil<AnchorPane, MessageRecordCmpController> fxmlCmpLoaderUtil
                    = new FxmlCmpLoaderUtil<>("client", "MessageRecordCmp.fxml",
                    messageList.get(i).getSendFrom().toString(), messageList.get(i).getMessageContent(), messageList.get(i).getSendTime().toLocalDateTime().toString());
            messageBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }

}
