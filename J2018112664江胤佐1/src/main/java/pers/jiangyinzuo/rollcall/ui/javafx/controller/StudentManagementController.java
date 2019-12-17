package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.service.StudentService;
import pers.jiangyinzuo.rollcall.service.impl.StudentServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;

/**
 * @author Jiang Yinzuo
 */
public class StudentManagementController {

    @FXML
    private TextField studentIdField;

    @FXML
    private Button queryBtn;

    @FXML
    private TextField studentNameText;

    @FXML
    private TextField majorText;

    @FXML
    private Button editStudentBtn;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private Button addStudentBtn;

    private StudentService studentService = new StudentServiceImpl();

    private Student student = new Student();

    private void clear() {
        studentIdField.setText("");
        studentNameText.setText("");
        majorText.setText("");
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        if (validateId()) {
            studentService.deleteStudent(student.getStudentId());
            CustomAlertBoard.showAlert("删除成功");
            clear();
        }
    }

    @FXML
    void editStudent(ActionEvent event) {
        if (validateId()) {
            student.setGender(maleRadio.isSelected());
            student.setMajor(majorText.getText());
            student.setStudentName(studentNameText.getText());

            studentService.updateStudent(student);
        }
    }

    @FXML
    void queryStudent(ActionEvent event) {
        if (validateId()) {
            student = studentService.queryStudent(student.getStudentId());
            maleRadio.setSelected(student.getGender());
            femaleRadio.setSelected(!student.getGender());
            majorText.setText(student.getMajor());
            studentNameText.setText(student.getStudentName());
        }
    }

    @FXML
    void addStudent(ActionEvent event) {
        if (validateId()) {
            student.setGender(maleRadio.isSelected());
            student.setMajor(majorText.getText());
            student.setStudentName(studentNameText.getText());

            studentService.insertStudent(student);
            CustomAlertBoard.showAlert("添加成功");
            clear();
        }
    }

    private boolean validateId() {
        if (studentIdField.getText() == null || studentIdField.getText().isBlank()) {
            CustomAlertBoard.showAlert("ID不能为空");
            return false;
        }
        try {
            student.setStudentId(Long.parseLong(studentIdField.getText()));
        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("ID格式错误");
            return false;
        }

        return true;
    }
}
