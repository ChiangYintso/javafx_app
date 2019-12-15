package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.StudentListController;

public class StudentCmpController implements FxController {

    @FXML
    private Text studentIdText;

    @FXML
    private Text studentNameText;

    @FXML
    private Text genderText;

    @FXML
    private Text majorText;

    @FXML
    private Button removeBtn;

    private Student student;

    private StudentListController studentListControllerCallBack;

    private AnchorPane pane;

    @FXML
    void removeStudent(ActionEvent event) {
        studentListControllerCallBack.removeStudent(pane, student);
    }

    /**
     * ≥ı ºªØ
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        this.student = (Student) params[0];
        this.studentListControllerCallBack = (StudentListController) params[1];
        studentIdText.setText(student.getStudentId().toString());
        studentNameText.setText(student.getStudentName());
        genderText.setText(student.getGenderStr());
        majorText.setText(student.getMajor());
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }
}
