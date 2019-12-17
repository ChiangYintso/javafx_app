package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
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

    @FXML
    private Button studentManagementBtn;

    @FXML
    private MenuItem rollCallBtn;

    @FXML
    private MenuItem showStudentListBtn;

    @FXML
    private MenuItem editClassBtn;

    /**
     * ��ѧ����
     */
    private TeachingClass teachingClass;

    @FXML
    void editClass(ActionEvent event) {
        SelectedTeachingClassState.getSingleton().setCls(teachingClass);
        StageManager.showTempStage("�޸Ľ�ѧ��", "EditClass.fxml");
    }

    public void init(TeachingClass teachingClass) {
        this.teachingClassName.setText(teachingClass.getClassName());
        this.teachingClassId.setText(teachingClass.getClassId().toString());
        this.schedule.setText(teachingClass.getWeeks() + "  ��" + teachingClass.getSession() / 10 + "��" + teachingClass.getSession() % 10 + "��");
        this.teachingClass = teachingClass;
        this.teachingClassPane.setStyle("-fx-background-color: #a8cadd");
    }

    @FXML
    void onMouseEntered(MouseEvent event) {
        teachingClassPane.setStyle("-fx-background-color: #b8c8cc");
    }

    @FXML
    void onMouseExited(MouseEvent event) {
        teachingClassPane.setStyle("-fx-background-color:  #a8cadd");
    }

    @FXML
    void rollCall(ActionEvent event) {
        SelectedTeachingClassState.getSingleton().setCls(teachingClass);
        StageManager.showTempStage("�������", "RollCallBoard.fxml");
    }

    @FXML
    void showStudentList(ActionEvent event) {
        SelectedTeachingClassState.getSingleton().setCls(teachingClass);
        StageManager.showTempStage("��ѧ����", "StudentList.fxml");
    }
}
