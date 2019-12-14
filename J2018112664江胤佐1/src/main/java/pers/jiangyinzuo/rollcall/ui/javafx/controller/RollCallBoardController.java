package pers.jiangyinzuo.rollcall.ui.javafx.controller;


import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.common.RollCallManager;
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
    
    private TeachingClass selectedTeachingClass = SelectedTeachingClassState.getSingleton().getCls();

    private List<RollCall> teachingClassRollCallList;
    
    private RollCallService rollCallService = new RollCallServiceImpl();;
    
    @FXML
    public void initialize() {
    	teachingClassName.setText(selectedTeachingClass.getClassName());
    }

    @FXML
    void abnormalRollCall(ActionEvent event) {
    	List<Student> student = RollCallManager.getAbnormalStudent(this.teachingClassRollCallList);
    }

    @FXML
    void randomRollCall(ActionEvent event) {

    }

    @FXML
    void wholeRollCall(ActionEvent event) {

    }


}
