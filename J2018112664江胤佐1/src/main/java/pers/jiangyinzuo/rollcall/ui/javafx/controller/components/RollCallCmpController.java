package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;

import java.sql.Timestamp;

public class RollCallCmpController implements FxController {

    @FXML
    private Text studentNameText;

    @FXML
    private Text studentIdText;

    @FXML
    private MenuButton presence;

    @FXML
    private MenuItem item1;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item3;

    @FXML
    private MenuItem item4;

    @FXML
    void changePresence(ActionEvent event) {
        MenuItem menuItem = (MenuItem)event.getSource();
        String tempPresence = menuItem.getText();
        menuItem.setText(presence.getText());
        presence.setText(tempPresence);
        rollCallCallBack.setPresence(presence.getText());
    }

    private Student student;

    private RollCall rollCallCallBack;

    /**
     * ≥ı ºªØ
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        this.student = (Student) params[0];
        this.rollCallCallBack = (RollCall) params[1];
        this.studentIdText.setText(student.getStudentId().toString());
        this.studentNameText.setText(student.getStudentName());
        rollCallCallBack.setPresence(RollCall.PRESENCE);
        rollCallCallBack.setRollCallTime(new Timestamp(System.currentTimeMillis()));
        rollCallCallBack.setStudent(student);
    }
}
