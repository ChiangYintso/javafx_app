package pers.jiangyinzuo.chat.server.javafx.controller.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.Broker;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.helper.JsonHelper;
import pers.jiangyinzuo.chat.server.javafx.GuiBroker;

/**
 * @author Jiang Yinzuo
 */
public class ToReviewCmpController extends AbstractToDoCmpController implements FxController {

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

    @FXML
    void agree(ActionEvent event) {

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
     * "master": <创立者>
     * }
     * }
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        JsonNode rawJson = (JsonNode) params[0];
        option.setText(JsonHelper.getJsonOption(rawJson));
        userIdText.setText((String) params[1]);


        JsonNode data = rawJson.get("data");
        groupName.setText(data.get("groupName").asText());
        groupIntro.setText(data.get("groupIntro").asText());
    }

    public void setSelf(Parent self) {
        this.self = (AnchorPane) self;
    }
}
