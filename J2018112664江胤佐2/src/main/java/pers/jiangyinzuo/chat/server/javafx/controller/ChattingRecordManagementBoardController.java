package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.domain.entity.Message;
import pers.jiangyinzuo.chat.server.javafx.controller.components.MessageRecordCmpController;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class ChattingRecordManagementBoardController {

    @FXML
    private Button queryBtn;

    @FXML
    private RadioButton friendRadio;

    @FXML
    private ToggleGroup radio;

    @FXML
    private RadioButton groupRadio;

    @FXML
    private Label mainLabel;

    @FXML
    private Label subLabel;

    @FXML
    private TextField mainIdField;

    @FXML
    private TextField subIdField;

    @FXML
    private VBox messageBox;

    @FXML
    private DatePicker limitDatePicker;

    private MessageService messageService = new MessageServiceImpl();
    private boolean isGroupSelected = false;
    private List<Message> messageList = new ArrayList<>(10);

    private int start = 1;

    @FXML
    void onFriendSelected(ActionEvent event) {
        isGroupSelected = true;
        mainLabel.setText("用户id(必填)");
        subLabel.setText("好友id");
        start = 1;
    }

    @FXML
    void onGroupSelected(ActionEvent event) {
        isGroupSelected = false;
        mainLabel.setText("群聊id(必填)");
        subLabel.setText("用户id");
        start = 1;
    }

    @FXML
    void query(ActionEvent event) {
        start = 1;
        if (mainIdField.getText().isBlank()) {
            CustomAlertBoard.showAlert("ID不能为空");
            return;
        }
        long mainId;
        long subId = -1L;
        try {
            mainId = Long.parseLong(mainIdField.getText());
            if (!subIdField.getText().isBlank()) {
                subId = Long.parseLong(subIdField.getText());
            }
        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("ID格式错误");
            return;
        }

        if (mainId <= 0 || (subId <= 0 && !subIdField.getText().isBlank())) {
            CustomAlertBoard.showAlert("ID不能为负数");
            return;
        }

        messageBox.getChildren().clear();
        Timestamp timestamp;
        if (limitDatePicker.getValue() == null) {
            timestamp = new Timestamp(System.currentTimeMillis());
        } else {
            timestamp = new Timestamp(limitDatePicker.getValue().atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
        }
        if (isGroupSelected) {
            if (subId == -1L) {
                messageList = messageService.queryGroupMessage(mainId, start, 5, timestamp);
            } else {
                messageList = messageService.queryUserMessageInGroup(subId, mainId, start, 5, timestamp);
            }
        } else {
            if (subId == -1L) {
                messageList = messageService.queryUserMessageToAllFriends(mainId, start, 5, timestamp);
            } else {
                messageList = messageService.queryUserMessageToFriends(mainId, subId, start, 5, timestamp);
            }
        }

        for (int i = messageList.size() - 1; i >= 0; --i) {
            FxmlCmpLoaderUtil<AnchorPane, MessageRecordCmpController> fxmlCmpLoaderUtil
                    = new FxmlCmpLoaderUtil<>("server", "MessageRecordCmp.fxml",
                    messageList.get(i).getSendFrom().toString(), messageList.get(i).getMessageContent(), messageList.get(i).getSendTime().toLocalDateTime().toString());
            messageBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }

}
