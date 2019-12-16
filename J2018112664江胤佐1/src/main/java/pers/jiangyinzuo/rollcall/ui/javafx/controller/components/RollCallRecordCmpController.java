package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

/**
 * @author Jiang Yinzuo
 */
public class RollCallRecordCmpController implements FxController {

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;

    @FXML
    private Button editPresenceBtn;

    @FXML
    private Text studentId;

    @FXML
    private MenuButton menuBtn;

    @FXML
    private MenuItem item1;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item3;

    @FXML
    private MenuItem item4;

    @FXML
    private MenuItem item5;

    @FXML
    private MenuItem item6;

    @FXML
    private MenuItem item7;

    private RollCall rollCall;

    @FXML
    void changeType(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        menuBtn.setText(item.getText());
    }

    @FXML
    void editPresence(ActionEvent event) {
        RollCallService rollCallService = new RollCallServiceImpl();
        rollCall.setPresence(menuBtn.getText());
        rollCallService.editRollCall(rollCall);
        CustomAlertBoard.showAlert("修改成功");
    }

    /**
     * 初始化
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        rollCall = (RollCall)params[0];

        rollCallType.setText(rollCall.getRollCallTypeString());
        instant.setText(rollCall.getRollCallTime().toString());
        studentId.setText(rollCall.getStudentId().toString());
        className.setText(rollCall.getTeachingClass().getClassName());
        menuBtn.setText(rollCall.getPresence());

        if (UserState.getSingleton().getStudent() != null) {
            editPresenceBtn.setVisible(false);
            menuBtn.setDisable(true);
        }
    }
}
