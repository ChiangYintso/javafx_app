package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class RollCallDashBoardController {

    @FXML
    private ChoiceBox<String> choicePresence;
    
    @FXML
    public void initialize() {
    	choicePresence.setItems( FXCollections.<String>observableArrayList(
    			"����", "ȱ��", "�ٵ�", "����"
    			));
    }
}
