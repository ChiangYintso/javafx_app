package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.RollCallRecordCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
import pers.jiangyinzuo.rollcall.ui.javafx.utils.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

import java.util.List;

public class RollCallDetailController {

    @FXML
    private VBox rollCallRecordBox;

    @FXML
    private MenuButton filter;

    @FXML
    private MenuItem item0;


    @FXML
    private MenuItem item1;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item3;

    @FXML
    private MenuItem item4;

    @FXML
    private MenuItem item5;

    @FXML
    private MenuItem item6;

    @FXML
    private Button statisticBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Button preBtn;

    private List<RollCall> rollCallList;
    private RollCallService rollCallService = new RollCallServiceImpl();
    private int start = 0;
    private Long teachingClassId = SelectedTeachingClassState.getSingleton().getCls().getClassId();

    @FXML
    void changeRollCallType(ActionEvent event) {
        filter.setText(((MenuItem)event.getSource()).getText());
        start = 0;
        preBtn.setDisable(true);
        nextBtn.setDisable(false);
        rollCallList = rollCallService.queryRollCallsByTeachingClassId(teachingClassId, start, filter.getText());
        addRollCallToBox();
    }

    @FXML
    void showStatistic(ActionEvent event) {
        StageManager.showTempStage("Í³¼Æ", "RollCallStatistic.fxml");
    }

    @FXML
    void showNextRecord(ActionEvent event) {
        start += 10;
        rollCallList = rollCallService.queryRollCallsByTeachingClassId(teachingClassId, start, filter.getText());
        if (rollCallList.size() == 0) {
            nextBtn.setDisable(true);
        }
        preBtn.setDisable(false);
        addRollCallToBox();
    }

    @FXML
    void showPreRecord(ActionEvent event) {
        start -= 10;
        if (start >= 0) {
            rollCallList = rollCallService.queryRollCallsByTeachingClassId(teachingClassId, start, filter.getText());
        } else {
            preBtn.setDisable(true);
        }
        nextBtn.setDisable(false);
        addRollCallToBox();
    }

    @FXML
    public void initialize() {
        rollCallList = rollCallService.queryRollCallsByTeachingClassId(teachingClassId, start, filter.getText());
        addRollCallToBox();
    }

    private void addRollCallToBox() {
        rollCallRecordBox.getChildren().clear();
        for (RollCall rollCall : rollCallList) {
            FxmlCmpLoaderUtil<Pane, RollCallRecordCmpController> fxmlCmpLoaderUtil = new FxmlCmpLoaderUtil<>("RollCallRecordCmp.fxml", rollCall);
            rollCallRecordBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }

}
