package main.java.pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;

public class RollCallRecordCmpController {

    @FXML
    private Text prescene;

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;
    
    // ³õÊ¼»¯²Ù×÷
    public void init(RollCall rollCall) {
    	this.prescene.setText(rollCall.getPresence());
    	this.rollCallType.setText(rollCall.getRollCallType());
    	this.instant.setText(rollCall.getRollCallTime().toString());
    }

}
