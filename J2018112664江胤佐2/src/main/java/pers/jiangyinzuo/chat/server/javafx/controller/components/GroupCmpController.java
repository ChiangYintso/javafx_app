package pers.jiangyinzuo.chat.server.javafx.controller.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.ForwardingMessageManager;
import pers.jiangyinzuo.chat.server.TcpServer;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GroupCmpController implements FxController {

    @FXML
    private Text groupId;

    @FXML
    private Text groupNameText;

    @FXML
    private Button changeBlockStatBtn;

    @FXML
    private Text masterIdText;

    private Group group;
    private GroupService groupService = new GroupServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper();
    @FXML
    void changeBlockStat(ActionEvent event) {
        changeBlockStatBtn.setText(group.isBlocked() ? "·â½û" : "½â·â");
        group.changeBlockStat();
        groupService.editGroup(group);

        Map<String, Object> map = new HashMap<>(5);
        map.put("option", JsonHelper.Option.GROUP_BLOCK_CHANGED);
        Map<String, Object> data = new HashMap<>(5);
        data.put("sendFrom", -1L);
        data.put("sendTo", group.getGroupId());
        map.put("data", data);
        try {
            ForwardingMessageManager.getSingleton().forward(objectMapper.writeValueAsBytes(map), -1L);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Object... params) {
        group = (Group) params[0];
        groupNameText.setText(group.getGroupName());
        groupId.setText(group.getGroupId().toString());
        masterIdText.setText(group.getMaster().getUserId().toString());
        changeBlockStatBtn.setText(group.isBlocked() ? "½â·â" : "·â½û");
    }
}
