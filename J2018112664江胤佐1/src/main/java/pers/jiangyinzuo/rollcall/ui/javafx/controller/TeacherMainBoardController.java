package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.TeachingClassCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

public class TeacherMainBoardController {

    @FXML
    private Text titleText;

    @FXML
    private Button showScheduleBtn;

    @FXML
    private Text teacherName;

    @FXML
    private Text teacherId;

    @FXML
    private VBox teachingClassList;

    
    private TeachingClassService teachingClassService;

    
    // 教学班列表
    private List<TeachingClass> teachingClasses;

    @FXML
    void showSchedule(ActionEvent event) {
    	StageManager.showStage("课表", "Schedule.fxml");
    }

    @FXML
    void addTeachingClass(ActionEvent event) {
        StageManager.showTempStage("新建教学班", "AddTeachingClass.fxml");
    }

    @FXML
    public void initialize() {
        ControllerProxy.setTeacherMainBoardController(this);
    	this.teachingClassService = new TeachingClassServiceImpl();

    	this.addTeachingClassTab();
    	this.teacherName.setText(UserState.getSingleton().getTeacher().getTeacherName());
    	this.teacherId.setText(UserState.getSingleton().getTeacher().getTeacherId().toString());
        this.titleText.setText(UserState.getSingleton().getTeacher().getTitle());
    }

    /**
     * 添加教学班到列表
     */
    public void addTeachingClassTab() {
        this.teachingClassList.getChildren().clear();
        this.teachingClasses = this.teachingClassService.queryTeachingClassesByTeacherId(UserState.getSingleton().getTeacher().getTeacherId());
    	for (TeachingClass cls : this.teachingClasses) {
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "TeachingClassCmp.fxml"));

            // 必须调用此方法才能得到controller
            Pane pane = null;
            try {
                pane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TeachingClassCmpController cmpController = fxmlLoader.getController();
        	cmpController.init(cls);
        	this.teachingClassList.getChildren().add(pane);
    	}
    }

    @FXML
    void showStudentManagement(ActionEvent event) {
        StageManager.showTempStage("学生管理", "StudentManagement.fxml");
    }
}

