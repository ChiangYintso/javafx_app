package pers.jiangyinzuo.rollcall.ui.javafx.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.common.RollCallManager;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.RollCallCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.utils.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

/**
 * @author Jiang Yinzuo
 */
public class RollCallBoardController {

    @FXML
    private Text teachingClassName;

    @FXML
    private Button randomRollCallBtn;

    @FXML
    private Button abnormalRollCallBtn;

    @FXML
    private Button wholeRollCallBtn;

    @FXML
    private VBox rollCallBox;

    @FXML
    private Button submitRollCallBtn;

    @FXML
    private Button showRollCallRecordBtn;

    private TeachingClass selectedTeachingClass = SelectedTeachingClassState.getSingleton().getCls();

    private List<RollCall> teachingClassRollCallList = new ArrayList<>();

    private RollCallService rollCallService = new RollCallServiceImpl();

    private List<Student> studentList;

    @FXML
    public void initialize() {
        teachingClassName.setText(selectedTeachingClass.getClassName());
        studentList = selectedTeachingClass.getStudentList();
    }

    @FXML
    void abnormalRollCall(ActionEvent event) {
        rollCallBox.getChildren().clear();
        List<Student> student = RollCallManager.getAbnormalStudent(this.teachingClassRollCallList);
    }

    @FXML
    void randomRollCall(ActionEvent event) {
        rollCallBox.getChildren().clear();
    }

    @FXML
    void wholeRollCall(ActionEvent event) {
        rollCallBox.getChildren().clear();
        for (Student student : studentList) {
            RollCall rollCall = createRollCall(1L);
            teachingClassRollCallList.add(rollCall);
            FxmlCmpLoaderUtil<AnchorPane, RollCallCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil<>("RollCallCmp.fxml", student, rollCall);
            rollCallBox.getChildren().addAll(fxmlCmpLoaderUtil.getPane());
        }
    }

    private RollCall createRollCall(Long rollCallType) {
        return new RollCall.Builder()
                .rollCallType(rollCallType)
                .teachingClass(selectedTeachingClass)
                .build();
    }

    @FXML
    void showRollCallRecord(ActionEvent event) {

    }

    @FXML
    void submitRollCall(ActionEvent event) {
        Map<String, Integer> statisticMap = new HashMap<>(5);
        statisticMap.put(RollCall.PRESENCE, 0);
        statisticMap.put(RollCall.ABSENT, 0);
        statisticMap.put(RollCall.ASK_FOR_LEAVE, 0);
        statisticMap.put(RollCall.LATE, 0);
        statisticMap.put(RollCall.LEAVE_EARLY, 0);
        for (RollCall rollCall : teachingClassRollCallList) {
            statisticMap.put(rollCall.getPresence(), statisticMap.get(rollCall.getPresence()) + 1);
        }
        rollCallService.bulkInsertRollCalls(teachingClassRollCallList);
        CustomAlertBoard.showAlert(statisticMap.toString());

    }
}
