package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.TeacherMainBoardController;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

/**
 * @author Jiang Yinzuo
 */
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
    	this.schedule.setText("ÖÜ" + teachingClass.getWeeks() + "µÚ" + teachingClass.getSession() + "½²");
    	this.teachingClass = teachingClass;
    	this.teachingClassPane.setStyle("-fx-background-color: #88ccdd");
    }
    
    @FXML
    void onSelectClass(MouseEvent event) {
    	SelectedTeachingClassState.getSingleton().setCls(teachingClass);
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
