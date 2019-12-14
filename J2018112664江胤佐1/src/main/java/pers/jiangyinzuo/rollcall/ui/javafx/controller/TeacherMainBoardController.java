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
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;
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
    	if (SelectedTeachingClassState.getSingleton().getCls() == null) {
    		CustomAlertBoard.showAlert("��ѡ��༶");
    	} else {
    		SceneRouter.showTempStage("�������", "RollCallBoard.fxml");
    	}
    	
    }
    
    @FXML
    void onSelectTeachingClass(MouseEvent event) {
    }
    
    // ��ѧ���б�
    private List<TeachingClass> teachingClasses;

    @FXML
    void showSchedule(ActionEvent event) {
    	SceneRouter.showStage("�α�", "Schedule.fxml");
    }

    @FXML
    void addTeachingClass(ActionEvent event) {
        SceneRouter.showTempStage("�½���ѧ��", "AddTeachingClass.fxml");
    }

    @FXML
    public void initialize() {
    	this.teachingClassService = new TeachingClassServiceImpl();
    	this.teachingClasses = this.teachingClassService.queryTeachingClassesByTeacherId(UserInfo.getSingleton().getTeacher().getTeacherId());
    	this.addTeachingClassTab();
    	this.teacherName.setText(UserInfo.getSingleton().getTeacher().getTeacherName());
    	this.teacherId.setText(UserInfo.getSingleton().getTeacher().getTeacherId().toString());
    }
    
    // ��̬��ӿγ���Ϣ
    private void addTeachingClassTab() {
    	for (TeachingClass cls : this.teachingClasses) {
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "TeachingClassCmp.fxml"));

            // ������ô˷������ܵõ�controller
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
    
    public void changeTip(Integer classId) {
    	
    }

    @FXML
    void showStudentManagement(ActionEvent event) {
        SceneRouter.showTempStage("ѧ������", "StudentManagement.fxml");
    }
}

