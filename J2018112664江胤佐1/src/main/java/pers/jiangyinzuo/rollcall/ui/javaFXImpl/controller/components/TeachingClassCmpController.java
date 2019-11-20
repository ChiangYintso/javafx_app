package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class TeachingClassCmpController {

    @FXML
    private Text teachingClassName;
    
    @FXML
    private Text teachingClassId;
    
    public void init(String teachingClassName, Integer teachingClassId) {
    	this.teachingClassName.setText(teachingClassName);
    	this.teachingClassId.setText(teachingClassId.toString());
    }
}
