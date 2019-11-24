package main.java.pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

/**
 * @author Jiang Yinzuo
 */
public class ScheduleController {

	@FXML
	private GridPane schedule;

	private TeachingClassService teachingClassService;

	private List<TeachingClass> teachingClassList;

	@FXML
    public void initialize() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
    	teachingClassService = new TeachingClassServiceImpl();
    	if (UserInfo.getSingleton().getTeacher()!=null) {
    		this.teachingClassList = teachingClassService.queryTeachingClassesByTeacherId(UserInfo.getSingleton().getTeacher().getTeacherId());
    	} else if (UserInfo.getSingleton().getStudent()!=null) {
    		this.teachingClassList = teachingClassService.queryTeachingClassesByStudentId(UserInfo.getSingleton().getStudent().getStudentId());
    	}
    	
    	for (TeachingClass cls : teachingClassList) {
    		Text text = new Text();
    		text.setText(cls.getClassName());
    		text.setFont(new Font(20));
    		int session = cls.getSession();
    		schedule.add(text, session / 10, session % 10);
    	}
    	for (Node node : schedule.getChildren()) {
    		GridPane.setValignment(node, VPos.CENTER);
    		GridPane.setHalignment(node, HPos.CENTER);
    	}
    }
}
