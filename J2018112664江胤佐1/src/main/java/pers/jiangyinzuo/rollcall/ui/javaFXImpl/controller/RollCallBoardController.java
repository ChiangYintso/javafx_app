package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.RollCallServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;

public class RollCallBoardController {

    @FXML
    private Text teachingClassName;

    @FXML
    private Button randomRollCallBtn;

    @FXML
    private Button abnormalRollCallBtn;

    @FXML
    private Button wholeRollCallBtn;
    
    private TeachingClass selectedTeachingClass;
    
    private RollCallService rollCallService;
    
    @FXML
    public void initialize() throws ClassNotFoundException, CustomException, IOException {
    	selectedTeachingClass = SelectedTeachingClass.getSingleton().getCls();
    	teachingClassName.setText(selectedTeachingClass.getClassName());
    	rollCallService = new RollCallServiceImpl(selectedTeachingClass);
    }

    @FXML
    void abnormalRollCall(ActionEvent event) {
    	List<Student> student = rollCallService.getAbnormalStudent();
    }

    @FXML
    void randomRollCall(ActionEvent event) {

    }

    @FXML
    void wholeRollCall(ActionEvent event) {

    }

}
