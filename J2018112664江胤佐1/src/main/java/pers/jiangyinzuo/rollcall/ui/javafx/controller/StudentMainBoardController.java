package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

/**
 * @author Jiang Yinzuo
 */
public class StudentMainBoardController {

    @FXML
    private Button recordBtn;
    
    @FXML
    private Button scheduleBtn;

    @FXML
    private Text studentId;

    @FXML
    private Text studentName;

    @FXML
    private Text major;
    
    @FXML
    public void initialize() {
    	Student student = UserState.getSingleton().getStudent();
    	this.studentId.setText(student.getStudentId().toString());
    	this.studentName.setText(student.getStudentName());
    	this.major.setText(student.getMajor());
    }
    
    @FXML
    void showRollCallRecord(ActionEvent event) throws IOException {
    	StageManager.showStage("������¼", "RollCallRecord.fxml");
    }

    
    @FXML
    void showSchedule(ActionEvent event) throws IOException {
    	StageManager.showStage("�α�", "Schedule.fxml");
    }
}
