package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class RollCallDashBoardController {

    @FXML
    private ChoiceBox<String> choicePresence;
    
    @FXML
    public void initialize() {
    	choicePresence.setItems( FXCollections.<String>observableArrayList(
    			"³öÇÚ", "È±ÇÚ", "³Ùµ½", "ÔçÍË"
    			));
    }
}
