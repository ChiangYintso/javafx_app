package main.java.pers.jiangyinzuo.rollcall.ui.javaFXImpl.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.pers.jiangyinzuo.rollcall.service.RollCallService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.RollCallServiceImpl;

public class RollCallRecordController {

    @FXML
    private VBox rollCallRecordList;

    @FXML
    private Text prescene;

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;

    private RollCallService rollcallService;
    
    @FXML
    public void initialize() {

    }
    
    // 动态添加点名记录
    private void addRollCallRecord() {
    	
    }

}
