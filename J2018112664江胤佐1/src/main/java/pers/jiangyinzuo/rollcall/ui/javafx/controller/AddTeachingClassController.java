package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class AddTeachingClassController {

    @FXML
    private TextField classNameField;

    @FXML
    private TextField weekField;

    @FXML
    private TextField sessionField;

    @FXML
    private TextField courseCodeField;

    @FXML
    private TextField classroomField;

    @FXML
    private TextField introField;

    @FXML
    private TextField semesterField;

    @FXML
    private TextField weeksField;

    @FXML
    private Button addTeachingClassBtn;

    @FXML
    private TextField creditField;

    private TeachingClassService teachingClassService = new TeachingClassServiceImpl();

    @FXML
    void addTeachingClass(ActionEvent event) {
        TeachingClass teachingClass = new TeachingClass.Builder()
                .className(classNameField.getText())
                .classroom(classroomField.getText())
                .credit(Integer.parseInt(creditField.getText()))
                .courseCode(Long.parseLong(courseCodeField.getText()))
                .intro(introField.getText())
                .semester(Integer.parseInt(semesterField.getText()))
                .weeks(weeksField.getText())
                .session(Integer.parseInt(weekField.getText()+sessionField.getText()))
                .teacher(UserInfo.getSingleton().getTeacher())
                .build();
        teachingClassService.addTeachingClass(teachingClass);
    }

}
