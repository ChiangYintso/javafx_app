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
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @FXML
    private Button exportToExcelBtn;

    private List<Student> studentList;

    private Set<Long> studentIdSet = new HashSet<>();

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
    void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("文件类型", "*.xls"));
        fileChooser.setInitialFileName(selectedTeachingClass.getClassName() + "教学名单.xls");
        File xlsFile = fileChooser.showSaveDialog(new Stage());

        if (xlsFile != null) {
            if (xlsFile.exists()) {
                xlsFile.delete();
            }
            try {
                WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
                WritableSheet sheet = workbook.createSheet("sheet1", 0);
                sheet.addCell(new Label(0, 0, "学号"));
                sheet.addCell(new Label(1, 0, "姓名"));
                sheet.addCell(new Label(2, 0, "性别"));
                sheet.addCell(new Label(3, 0, "专业"));
                for (int row = 1, length = studentList.size(); row <= length; row++) {
                    sheet.addCell(new Label(0, row, studentList.get(row - 1).getStudentId().toString()));
                    sheet.addCell(new Label(1, row, studentList.get(row - 1).getStudentName()));
                    sheet.addCell(new Label(2, row, studentList.get(row - 1).getGenderStr()));
                    sheet.addCell(new Label(3, row, studentList.get(row - 1).getMajor()));
                }
                workbook.write();
                workbook.close();
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }
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
            Student student;
            int count = 0;
            for (int i = 1; i < rows; ++i) {
                student = new Student.Builder()
                        .studentId(Long.parseLong(sheet.getCell(0, i).getContents()))
                        .studentName(sheet.getCell(1, i).getContents())
                        .gender(sheet.getCell(2, i).getContents())
                        .major(sheet.getCell(3, i).getContents())
                        .build();
                if (!studentIdSet.contains(student.getStudentId())) {
                    addStudentToBox(student);
                    teachingClassService.addStudent(selectedTeachingClass.getClassId(), student.getStudentId());
                    studentService.insertStudent(student);
                    ++count;
                }
            }
            workbook.close();
            CustomAlertBoard.showAlert("成功导入" + count + "名学生");
        } catch (IOException | BiffException | NumberFormatException e) {
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
        studentIdSet.add(student.getStudentId());
        FxmlCmpLoaderUtil<AnchorPane, StudentCmpController> fxmlCmpLoaderUtil =
                new FxmlCmpLoaderUtil<>("StudentCmp.fxml", student, this);
        fxmlCmpLoaderUtil.getController().setPane(fxmlCmpLoaderUtil.getPane());
        studentBox.getChildren().add(fxmlCmpLoaderUtil.getPane());

    }

    public void removeStudent(AnchorPane pane, Student student) {
        this.studentBox.getChildren().remove(pane);
        this.studentList.remove(student);
        this.studentIdSet.remove(student.getStudentId());
        teachingClassService.removeStudent(selectedTeachingClass.getClassId(), student.getStudentId());
    }
}
