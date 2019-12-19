package pers.jiangyinzuo.chat.server.javafx.controller.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.dao.GroupDao;
import pers.jiangyinzuo.chat.dao.mysql.GroupDaoImpl;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.ForwardingMessageManager;
import pers.jiangyinzuo.chat.server.javafx.GuiBroker;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.NoticeService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;
import pers.jiangyinzuo.chat.service.impl.NoticeServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class ToReviewGroupCmpController extends AbstractToDoCmpController implements FxController {

    @FXML
    private Text option;

    @FXML
    private Text groupName;

    @FXML
    private Text groupIntro;

    @FXML
    private Button agreeBtn;

    @FXML
    private Button rejectBtn;

    @FXML
    private Text userIdText;

    private AnchorPane self;

    private Group group;

    private GroupService groupService = new GroupServiceImpl();

    private NoticeService noticeService = new NoticeServiceImpl();

    private JsonNode rawJson;

    @FXML
    void agree(ActionEvent event) {
        // 由服务端将建群信息提交到数据库
        groupService.foundGroup(group, Long.parseLong(userIdText.getText()));
        // 移除已处理的通知
        GuiBroker.getSingleMainBoardController().removeToDo(self);

        ObjectMapper objectMapper = new ObjectMapper();

        // 发送消息给客户端，提醒客户端查看通知
        Map<String, Object> noticeRoot = new HashMap<>(10);
        noticeRoot.put("option", JsonHelper.Option.FOUND_GROUP_ACCEPTED);
        Map<String, Object> data = new HashMap<>(10);
        data.put("sendTo", Integer.parseInt(userIdText.getText()));
        data.put("sendFrom", -1);
        noticeRoot.put("data", data);

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(noticeRoot);
            // 将通知存入数据库
            noticeService.insertNotice(bytes);
            // 由消息转发管理类转发消息
            ForwardingMessageManager.sendMessage(bytes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reject(ActionEvent event) {
        GuiBroker.getSingleMainBoardController().removeToDo(self);
    }

    /**
     * {
     * "option": Option.FOUND_GROUP,
     * "data": {
     * "groupName": <群聊名>,
     * "groupIntro": <群聊简介>,
     * "sendTo": <创立者>
     * }
     * }
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        rawJson = (JsonNode) params[0];
        option.setText(JsonHelper.getJsonOption(rawJson));
        userIdText.setText((String) params[1]);

        JsonNode data = rawJson.get("data");
        groupName.setText(data.get("groupName").asText());
        groupIntro.setText(data.get("groupIntro").asText());

        group = new Group.Builder()
                .groupName(groupName.getText())
                .groupIntro(groupIntro.getText()).build();
    }

    public void setSelf(Parent self) {
        this.self = (AnchorPane) self;
    }
}
