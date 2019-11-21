package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.TeacherMainBoardController;
import main.java.pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClass;

public class TeachingClassCmpController {
    @FXML
    private Pane teachingClassPane;
    
    @FXML
    private Text teachingClassName;
    
    @FXML
    private Text teachingClassId;
    
    @FXML
    private Text schedule;
    
    private TeachingClass teachingClass;
    
    public void init(TeachingClass teachingClass) {
    	this.teachingClassName.setText(teachingClass.getClassName());
    	this.teachingClassId.setText(teachingClass.getClassId().toString());
    	this.schedule.setText("ÖÜ" + teachingClass.getWeek() + "µÚ" + teachingClass.getSession() + "½²");
    	this.teachingClass = teachingClass;
    	this.teachingClassPane.setStyle("-fx-background-color: #88ccdd");
    }
    
    @FXML
    void onSelectClass(MouseEvent event) {
    	SelectedTeachingClass.getSingleton().setCls(teachingClass);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/components/" + "TeacherMainBoard.fxml"));
    	TeacherMainBoardController controller = fxmlLoader.getController();
    }
    
    @FXML
    void onMouseEntered(MouseEvent event) {
    	teachingClassPane.setStyle("-fx-background-color: #aabbcc");
    }

    @FXML
    void onMouseExited(MouseEvent event) {
    	teachingClassPane.setStyle("-fx-background-color: #88ccdd");
    }
}
