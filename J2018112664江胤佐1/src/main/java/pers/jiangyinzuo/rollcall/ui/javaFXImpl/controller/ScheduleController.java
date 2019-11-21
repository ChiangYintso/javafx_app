package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class ScheduleController {

    @FXML
    private GridPane schedule;

    private TeachingClassService teachingClassService;
    
    private List<TeachingClass> teachingClassList;
    
    @FXML
    public void initialize() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
    	teachingClassService = new TeachingClassServiceImpl();
    	this.teachingClassList = teachingClassService.queryTeachingClassesByTeacherId(UserInfo.getSingleton().getTeacher().getTeacherId());
    }
}

