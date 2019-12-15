package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.common.RollCallManager;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RollCallCmpController implements FxController {

    @FXML
    private Text studentNameText;

    @FXML
    private Text studentIdText;

    @FXML
    private MenuButton presence;

    @FXML
    private MenuItem item1;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item3;

    @FXML
    private Button submitBtn;

    @FXML
    private MenuItem item4;

    @FXML
    void changePresence(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        String tempPresence = menuItem.getText();
        menuItem.setText(presence.getText());
        presence.setText(tempPresence);
        rollCallCallBack.setPresence(presence.getText());
    }

    private Student student;

    private RollCall rollCallCallBack;

    private RollCallService rollCallService = new RollCallServiceImpl();

    @FXML
    void submit(ActionEvent event) {
        long rollCallType;
        long classId = SelectedTeachingClassState.getSingleton().getCls().getClassId();
        if (presence.getText().equals(RollCall.AWARD) || presence.getText().equals(RollCall.PUNISH)) {
            rollCallType = 2;
        } else {
            rollCallType = 1;
        }
        rollCallService.insertRollCall(student.getStudentId(),
                classId,
                presence.getText(), rollCallType);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        RollCallManager.setLastRollCall(classId, studentList);
    }

    /**
     * ≥ı ºªØ
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        this.student = (Student) params[0];
        this.rollCallCallBack = (RollCall) params[1];
        this.studentIdText.setText(student.getStudentId().toString());
        this.studentNameText.setText(student.getStudentName());
        if (params[2].equals(2L)) {
            presence.setText(RollCall.AWARD);
            item1.setText(RollCall.PUNISH);
            item2.setVisible(false);
            item3.setVisible(false);
            item4.setVisible(false);
            rollCallCallBack.setPresence(RollCall.AWARD);
        } else {
            rollCallCallBack.setPresence(RollCall.PRESENCE);
        }

        rollCallCallBack.setRollCallTime(new Timestamp(System.currentTimeMillis()));
        rollCallCallBack.setStudent(student);
    }
}
