package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

public class EditClassController {

    @FXML
    private TextField classNameField;

    @FXML
    private TextField weekField;

    @FXML
    private TextField sessionField;

    @FXML
    private TextField classroomField;

    @FXML
    private TextField introField;

    @FXML
    private TextField creditField;

    @FXML
    private TextField weeksField;

    private TeachingClass selectedTeachingClass;

    private Stage stage;

    private TeachingClassService teachingClassService = new TeachingClassServiceImpl();

    @FXML
    public void initialize() {
        stage = StageManager.getCurrentStage();
        selectedTeachingClass = SelectedTeachingClassState.getSingleton().getCls();
        classNameField.setText(selectedTeachingClass.getClassName());
        weekField.setText(String.valueOf(selectedTeachingClass.getSession() / 10));
        sessionField.setText(String.valueOf(selectedTeachingClass.getSession() % 10));
        weeksField.setText(selectedTeachingClass.getWeeks());
        introField.setText(selectedTeachingClass.getIntro());
        creditField.setText(selectedTeachingClass.getCredit().toString());
        classroomField.setText(selectedTeachingClass.getClassroom());
    }

    @FXML
    void deleteClass(ActionEvent event) {
        teachingClassService.deleteClass(selectedTeachingClass.getClassId());
        ControllerProxy.getTeacherMainBoardController().addTeachingClassTab();
        stage.close();
    }

    @FXML
    void editClass(ActionEvent event) {
        selectedTeachingClass.setClassName(classNameField.getText());
        selectedTeachingClass.setClassroom(classroomField.getText());
        selectedTeachingClass.setCredit(Integer.parseInt(creditField.getText()));
        selectedTeachingClass.setWeeks(weeksField.getText());
        selectedTeachingClass.setSession(Integer.parseInt(weekField.getText() + sessionField.getText()));
        selectedTeachingClass.setIntro(introField.getText());

        teachingClassService.editClass(selectedTeachingClass);
        CustomAlertBoard.showAlert("ÐÞ¸Ä³É¹¦");
        ControllerProxy.getTeacherMainBoardController().addTeachingClassTab();
    }
}
