package pers.jiangyinzuo.chat.server.javafx.controller.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.ForwardingMessageManager;
import pers.jiangyinzuo.chat.server.TcpServer;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class UserStatusCmpController implements FxController {

    @FXML
    private Text userIdText;

    @FXML
    private Text userNameText;

    @FXML
    private Text isOnlineText;

    @FXML
    private Button changeBlockStatusBtn;

    private User user;
    private ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    void changeBlockStatus(ActionEvent event) {
        AccountService accountService = new AccountServiceImpl();
        user.setBlocked(!user.isBlocked());
        // 更新数据库中的用户状态信息
        accountService.updateUserInfo(user);
        changeBlockStatusBtn.setText(user.isBlocked() ? "解禁" : "封禁");

        // 将通知通过TCP服务器发送给用户，实现实时禁言
        Map<String, Object> map = new HashMap<>(5);
        map.put("option", JsonHelper.Option.UPDATE_USER_INFO);
        Map<String, Object> data = new HashMap<>(5);
        data.put("sendFrom", -1);
        data.put("sendTo", user.getUserId());
        map.put("data", data);
        try {
            ForwardingMessageManager.sendMessage(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Object... params) {
        user = (User) params[0];
        userIdText.setText(user.getUserId().toString());
        userNameText.setText(user.getUserName());
        isOnlineText.setText(TcpServer.userIsOnline(user.getUserId()) ? "在线" : "离线");
        changeBlockStatusBtn.setText(user.isBlocked() ? "解禁" : "封禁");
    }
}
