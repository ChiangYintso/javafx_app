package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class RollCallRecordController {

    @FXML
    private VBox rollCallRecordList;

    @FXML
    private Text prescene;

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;

    private RollCallService rollcallService = new RollCallServiceImpl();
    private Student student;

    private List<RollCall> rollCallList;

    @FXML
    public void initialize() {
        student = UserState.getSingleton().getStudent();
        rollCallList = rollcallService.queryRollCallsByStudentId(student.getStudentId());
        showRollCallRecord();
    }

    private void showRollCallRecord() {

    }
}
