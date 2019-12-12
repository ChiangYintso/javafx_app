package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
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

    private List<RollCall> teachingClassRollCallList;
    
    private RollCallService rollCallService;
    
    @FXML
    public void initialize() throws ClassNotFoundException, CustomException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    	selectedTeachingClass = SelectedTeachingClass.getSingleton().getCls();
    	teachingClassName.setText(selectedTeachingClass.getClassName());
    	rollCallService = new RollCallServiceImpl(selectedTeachingClass);
    }

    @FXML
    void abnormalRollCall(ActionEvent event) {
    	List<Student> student = getAbnormalStudent();
    	
    }

    @FXML
    void randomRollCall(ActionEvent event) {

    }

    @FXML
    void wholeRollCall(ActionEvent event) {

    }

    private List<Student> getAbnormalStudent() {
        List<Student> abnormalStudentList = new ArrayList<>();
        for (RollCall r : this.teachingClassRollCallList) {
            if (!r.getPresence().equals("ÒÑµ½")) {
                abnormalStudentList.add(r.getStudent());
            }
        }
        return abnormalStudentList;
    }

    private List<Student> getRandomStudent(int count) {
		List<Student> studentList = this.selectedTeachingClass.getStudentList();
		if (count < 0 || count > studentList.size()) {
			return studentList;
		}
		List<Student> resultList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		while (set.size() < count) {
			set.add((int) (Math.random() * studentList.size()));
		}
		for (Integer i : set) {
			resultList.add(studentList.get(i));
		}
		return resultList;
    }


}
