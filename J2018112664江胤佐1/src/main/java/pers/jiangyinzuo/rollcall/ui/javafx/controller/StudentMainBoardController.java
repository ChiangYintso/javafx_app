package main.java.pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class StudentMainBoardController {

    @FXML
    private Button recordBtn;
    
    @FXML
    private Button scheduleBtn;

    @FXML
    private Button teachingClassBtn;

    @FXML
    private Text studentId;

    @FXML
    private Text studentName;

    @FXML
    private Text major;
    
    @FXML
    public void initialize() {
    	Student student = UserInfo.getSingleton().getStudent();
    	this.studentId.setText(student.getStudentId().toString());
    	this.studentName.setText(student.getStudentName());
    	this.major.setText(student.getMajor());
    }
    
    @FXML
    void showRollCallRecord(ActionEvent event) throws IOException {
    	SceneRouter.showStage("点名记录", "RollCallRecord.fxml");
    }

    
    @FXML
    void showSchedule(ActionEvent event) throws IOException {
    	SceneRouter.showStage("课表", "Schedule.fxml");
    }
}
