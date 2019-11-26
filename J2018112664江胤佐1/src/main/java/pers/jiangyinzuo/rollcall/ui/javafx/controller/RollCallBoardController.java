package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;

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
    public void initialize() throws ClassNotFoundException, CustomException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
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
