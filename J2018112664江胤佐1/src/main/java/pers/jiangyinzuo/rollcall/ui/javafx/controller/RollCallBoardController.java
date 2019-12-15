package pers.jiangyinzuo.rollcall.ui.javafx.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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


    @FXML
    private RadioButton rollCallRadio;

    @FXML
    private RadioButton questionRadio;

    @FXML
    private ToggleGroup rollCallTypeToggleGroup;

    @FXML
    private Button repeatRollCallBtn;

    @FXML
    private TextField randomTotal;

    private TeachingClass selectedTeachingClass = SelectedTeachingClassState.getSingleton().getCls();

    private List<RollCall> teachingClassRollCallList = new ArrayList<>();

    private RollCallService rollCallService = new RollCallServiceImpl();

    private List<Student> studentList;

    private List<Student> totalStudentList;

    private Long rollCallType = 1L;

    @FXML
    void chooseRollCallType(ActionEvent event) {
        if (event.getSource().equals(randomRollCallBtn)) {
            rollCallType = 1L;
        } else {
            rollCallType = 2L;
        }
        rollCallBox.getChildren().clear();
    }

    @FXML
    public void initialize() {
        teachingClassName.setText(selectedTeachingClass.getClassName());
        studentList = selectedTeachingClass.getStudentList();
        totalStudentList = selectedTeachingClass.getStudentList();
    }

    @FXML
    void abnormalRollCall(ActionEvent event) {
        studentList = rollCallService.queryAbnormalStudent(selectedTeachingClass.getClassId());
        if (studentList.size() == 0) {
            CustomAlertBoard.showAlert("没有异常");
        } else {
            addToStudentBox(studentList);
        }

    }

    @FXML
    void randomRollCall(ActionEvent event) {
        int total;
        try {
            total = Integer.parseInt(randomTotal.getText());
        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("请输入数字 1 - " + totalStudentList.size());
            return;
        }
        if (total > totalStudentList.size() || total < 1) {
            CustomAlertBoard.showAlert("请输入数字 1 - " + totalStudentList.size());
            return;
        }
        studentList = rollCallService.queryAbnormalStudent(selectedTeachingClass.getClassId());

        addToStudentBox(studentList);
    }

    @FXML
    void wholeRollCall(ActionEvent event) {
        studentList = totalStudentList;
        addToStudentBox(studentList);
    }

    private void addToStudentBox(List<Student> students) {
        rollCallBox.getChildren().clear();
        teachingClassRollCallList.clear();
        for (Student student : students) {
            RollCall rollCall = createRollCall(rollCallType);
            teachingClassRollCallList.add(rollCall);
            FxmlCmpLoaderUtil<AnchorPane, RollCallCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil<>("RollCallCmp.fxml", student, rollCall, rollCallType);
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
        if (rollCallType == 1L) {
            statisticMap.put(RollCall.PRESENCE, 0);
            statisticMap.put(RollCall.ABSENT, 0);
            statisticMap.put(RollCall.ASK_FOR_LEAVE, 0);
            statisticMap.put(RollCall.LATE, 0);
            statisticMap.put(RollCall.LEAVE_EARLY, 0);
        } else {
            statisticMap.put(RollCall.AWARD, 0);
            statisticMap.put(RollCall.PUNISH, 0);
        }

        for (RollCall rollCall : teachingClassRollCallList) {
            statisticMap.put(rollCall.getPresence(), statisticMap.get(rollCall.getPresence()) + 1);
        }
        rollCallService.bulkInsertRollCalls(teachingClassRollCallList);
        statistic(statisticMap, teachingClassRollCallList.size());
        RollCallManager.setLastRollCall(selectedTeachingClass.getClassId(), studentList);
    }

    @FXML
    void repeatRollCall(ActionEvent event) {
        studentList = RollCallManager.getLastRollCall(selectedTeachingClass.getClassId());
        if (studentList != null) {
            addToStudentBox(studentList);
        }
    }

    private void statistic(Map<String, Integer> statisticMap, int total) {
        StringBuilder result = new StringBuilder("点名结果汇总: \n" + "共点名" + total + "人\n");
        for (String presence : statisticMap.keySet()) {
            result.append(presence + statisticMap.get(presence) + "人, 占" + String.format("%.2f", (double) statisticMap.get(presence) / total * 100) + "%\n");
        }
        CustomAlertBoard.showAlert(result.toString());
    }
}
