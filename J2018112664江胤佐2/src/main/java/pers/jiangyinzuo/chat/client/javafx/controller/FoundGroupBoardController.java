package pers.jiangyinzuo.chat.client.javafx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pers.jiangyinzuo.chat.client.javafx.Main;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.helper.JsonHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class FoundGroupBoardController {

    @FXML
    private TextField groupName;

    @FXML
    private TextField groupIntro;

    @FXML
    private Button applyBtn;

    @FXML
    void apply(ActionEvent event) {
        if (groupName.getText() == null ||groupName.getText().isBlank()) {
            CustomAlertBoard.showAlert("群名称不能为空");
        }

        /*
            发送JSON到服务器
            {
                "option": Option.FOUND_GROUP,
                "data": {
                    "groupName": <群聊名>,
                    "groupIntro": <群聊简介>,
                    "master": <创立者>
                }
            }
         */
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>(10);
        map.put("option", JsonHelper.Option.FOUND_GROUP);

        Map<String, Object> groupMap = new HashMap<>(10);
        groupMap.put("groupName", groupName.getText());
        groupMap.put("groupIntro", groupIntro.getText());
        groupMap.put("master", UserState.getSingleton().getUser().getUserId());

        map.put("data", groupMap);
        Main.getClientThreadPool().execute(() -> {
            try {
                Main.getTcpClient().sendMessage(objectMapper.writeValueAsBytes(map));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
