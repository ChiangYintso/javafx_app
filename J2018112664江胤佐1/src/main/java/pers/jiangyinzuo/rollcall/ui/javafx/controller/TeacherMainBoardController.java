package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.TeachingClassCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;
import pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class TeacherMainBoardController {

    @FXML
    private Button showScheduleBtn;

    @FXML
    private Text teacherName;

    @FXML
    private Text teacherId;

    @FXML
    private VBox teachingClassList;
    
    @FXML
    private Button rollCallBtn;

    @FXML
    private Text selectedTip;
    
    private TeachingClassService teachingClassService;

    @FXML
    void rollCall(ActionEvent event) throws IOException {
    	if (SelectedTeachingClass.getSingleton().getCls() == null) {
    		CustomAlertBoard.showAlert("请选择班级");
    	} else {
    		SceneRouter.showTempStage("点名面板", "RollCallBoard.fxml");
    	}
    	
    }
    
    @FXML
    void onSelectTeachingClass(MouseEvent event) {
    }
    
    // 教学班列表
    private List<TeachingClass> teachingClasses;

    @FXML
    void showSchedule(ActionEvent event) throws IOException {
    	SceneRouter.showStage("课表", "Schedule.fxml");
    }
    
    @FXML
    public void initialize() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
    	this.teachingClassService = new TeachingClassServiceImpl();
    	this.teachingClasses = this.teachingClassService.queryTeachingClassesByTeacherId(UserInfo.getSingleton().getTeacher().getTeacherId());
    	this.addTeachingClassTab();
    	this.teacherName.setText(UserInfo.getSingleton().getTeacher().getTeacherName());
    	this.teacherId.setText(UserInfo.getSingleton().getTeacher().getTeacherId().toString());
    }
    
    // 动态添加课程信息
    private void addTeachingClassTab() throws IOException {
    	for (TeachingClass cls : this.teachingClasses) {
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "TeachingClassCmp.fxml"));

            // 必须调用此方法才能得到controller
        	Pane pane = fxmlLoader.load();
        	TeachingClassCmpController cmpController = fxmlLoader.getController();
        	cmpController.init(cls);
        	this.teachingClassList.getChildren().add(pane);
    	}
    }
    
    public void changeTip(Integer classId) {
    	
    }
}

