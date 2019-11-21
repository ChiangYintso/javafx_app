package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class TeachingClassCmpController {

    @FXML
    private Text teachingClassName;
    
    @FXML
    private Text teachingClassId;
    
    @FXML
    private Text schedule;
    
    public void init(String teachingClassName, Integer teachingClassId, Integer week, Integer session) {
    	this.teachingClassName.setText(teachingClassName);
    	this.teachingClassId.setText(teachingClassId.toString());
    	this.schedule.setText("ÖÜ" + week + "µÚ" + session + "½²");
    }
}
