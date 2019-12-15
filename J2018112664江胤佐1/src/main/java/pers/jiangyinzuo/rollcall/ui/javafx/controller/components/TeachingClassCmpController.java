package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.TeacherMainBoardController;
import pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;
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

    }

    public void init(TeachingClass teachingClass) {
        this.teachingClassName.setText(teachingClass.getClassName());
        this.teachingClassId.setText(teachingClass.getClassId().toString());
        this.schedule.setText(teachingClass.getWeeks() + "  ��" + teachingClass.getSession() / 10 + "��" + teachingClass.getSession() % 10 + "��");
        this.teachingClass = teachingClass;
        this.teachingClassPane.setStyle("-fx-background-color: #88ccdd");
    }

    @Deprecated
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

    @FXML
    void rollCall(ActionEvent event) {
        SelectedTeachingClassState.getSingleton().setCls(teachingClass);
        SceneRouter.showTempStage("�������", "RollCallBoard.fxml");
    }

    @FXML
    void showStudentList(ActionEvent event) {

    }
}
