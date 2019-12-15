package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.service.StudentService;
import pers.jiangyinzuo.rollcall.service.TeachingClassService;
import pers.jiangyinzuo.rollcall.service.impl.StudentServiceImpl;
import pers.jiangyinzuo.rollcall.service.impl.TeachingClassServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.StudentCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.utils.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class StudentListController {

    @FXML
    private TextField newStudentIdText;

    @FXML
    private Button addStudentBtn;

    @FXML
    private VBox studentBox;

    @FXML
    private Button importBtn;

    private List<Student> studentList;

    private TeachingClass selectedTeachingClass;

    private TeachingClassService teachingClassService = new TeachingClassServiceImpl();
    private StudentService studentService = new StudentServiceImpl();

    @FXML
    void addStudent(ActionEvent event) {
        Long studentId;
        try {
            studentId = Long.parseLong(newStudentIdText.getText());

        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("学号格式错误");
            return;
        }
        Student student = studentService.queryStudent(studentId);
        if (student == null) {
            CustomAlertBoard.showAlert("该学生不存在");
            return;
        }
        teachingClassService.addStudent(selectedTeachingClass.getClassId(), studentId);

        addStudentToBox(student);
    }

    @FXML
    void importFromExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("文件类型", "*.xls"));
        File xlsFile = fileChooser.showOpenDialog(new Stage());
        try {
            Workbook workbook = Workbook.getWorkbook(xlsFile);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            int columns = sheet.getColumns();
            Cell cell;
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    cell = sheet.getCell(j, i);
                    CustomAlertBoard.showAlert(cell.getContents());
                }
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            CustomAlertBoard.showAlert("导入失败");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        selectedTeachingClass = SelectedTeachingClassState.getSingleton().getCls();
        studentList = selectedTeachingClass.getStudentList();

        for (Student student : studentList) {
            addStudentToBox(student);
        }
    }

    private void addStudentToBox(Student student) {
        FxmlCmpLoaderUtil<AnchorPane, StudentCmpController> fxmlCmpLoaderUtil =
                new FxmlCmpLoaderUtil<>("StudentCmp.fxml", student, this);
        fxmlCmpLoaderUtil.getController().setPane(fxmlCmpLoaderUtil.getPane());
        studentBox.getChildren().add(fxmlCmpLoaderUtil.getPane());

    }

    public void removeStudent(AnchorPane pane, Student student) {
        this.studentBox.getChildren().remove(pane);
        this.studentList.remove(student);
        teachingClassService.removeStudent(selectedTeachingClass.getClassId(), student.getStudentId());
    }
}
