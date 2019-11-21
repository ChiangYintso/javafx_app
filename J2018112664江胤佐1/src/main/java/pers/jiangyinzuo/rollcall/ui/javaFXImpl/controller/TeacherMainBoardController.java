package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeacherService;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeacherServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.components.TeachingClassCmpController;
import main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.router.SceneRouter;
import main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.utils.FXMLTool;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class TeacherMainBoardController {

    @FXML
    private Button showScheduleBtn;

    @FXML
    private Text teacherName;

    @FXML
    private Text teacherId;

    @FXML
    private VBox teachingClassList;
    
    private TeachingClassService teachingClassService;
    
    // ��ѧ���б�
    private List<TeachingClass> teachingClasses;

    @FXML
    void showSchedule(ActionEvent event) throws IOException {
    	SceneRouter.showStage("�α�", "Schedule.fxml");
    }
    
    @FXML
    public void initialize() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
    	this.teachingClassService = new TeachingClassServiceImpl();
    	this.teachingClasses = this.teachingClassService.queryTeachingClassesByTeacherId(UserInfo.getSingleton().getTeacher().getTeacherId());
    	this.addTeachingClassTab();
    }
    
    // ��̬��ӿγ���Ϣ
    private void addTeachingClassTab() throws IOException {
    	for (TeachingClass cls : this.teachingClasses) {
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "TeachingClassCmp.fxml"));
        	Pane pane = fxmlLoader.load();
        	TeachingClassCmpController cmpController = fxmlLoader.getController();
        	cmpController.init(cls.getClassName(), cls.getClassId(), cls.getWeek(), cls.getSession());
        	this.teachingClassList.getChildren().add(pane);
    	}
    }
}

