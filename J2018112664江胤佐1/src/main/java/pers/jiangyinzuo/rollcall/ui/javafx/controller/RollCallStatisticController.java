package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import pers.jiangyinzuo.rollcall.domain.dto.StudentRollCallResultDTO;
import pers.jiangyinzuo.rollcall.service.RollCallService;
import pers.jiangyinzuo.rollcall.service.impl.RollCallServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.components.RollCallStatisticCmpController;
import pers.jiangyinzuo.rollcall.ui.javafx.utils.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.rollcall.ui.state.SelectedTeachingClassState;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class RollCallStatisticController {

    @FXML
    private Button exportBtn;

    @FXML
    private VBox abnormalStudentBox;
    private List<StudentRollCallResultDTO> dtoList;

    @FXML
    void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("文件类型", "*.xls"));
        fileChooser.setInitialFileName(SelectedTeachingClassState.getSingleton().getCls().getClassName() + "考勤统计.xls");
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
                sheet.addCell(new Label(2, 0, "已到"));
                sheet.addCell(new Label(3, 0, "未到"));
                sheet.addCell(new Label(4, 0, "迟到"));
                sheet.addCell(new Label(5, 0, "早退"));
                sheet.addCell(new Label(6, 0, "请假"));
                sheet.addCell(new Label(7, 0, "异常出勤"));
                for (int row = 1, length = dtoList.size(); row <= length; row++) {
                    sheet.addCell(new Label(0, row, dtoList.get(row - 1).getStudentId().toString()));
                    sheet.addCell(new Label(1, row, dtoList.get(row - 1).getStudentName()));
                    sheet.addCell(new Label(2, row, dtoList.get(row - 1).getPresenceCount().toString()));
                    sheet.addCell(new Label(3, row, dtoList.get(row - 1).getAbsentCount().toString()));
                    sheet.addCell(new Label(4, row, dtoList.get(row - 1).getLateCount().toString()));
                    sheet.addCell(new Label(5, row, dtoList.get(row - 1).getLeaveEarlyCount().toString()));
                    sheet.addCell(new Label(6, row, dtoList.get(row - 1).getAskForLeaveCount().toString()));
                    sheet.addCell(new Label(7, row, dtoList.get(row - 1).getAbnormalCount().toString()));
                }
                workbook.write();
                workbook.close();
                CustomAlertBoard.showAlert("保存成功");
            } catch (IOException | WriteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        RollCallService rollCallService = new RollCallServiceImpl();
        // 根据教学班ID获取点名结果记录
        dtoList = rollCallService.queryRollCallStatistic(
                SelectedTeachingClassState.getSingleton().getCls()
                        .getClassId());
        // 根据异常点名次数排序
        Collections.sort(dtoList);
        // 将点名结果动态加载到界面上
        for (StudentRollCallResultDTO dto : dtoList) {
            FxmlCmpLoaderUtil<AnchorPane, RollCallStatisticCmpController> fxmlCmpLoaderUtil =
                    new FxmlCmpLoaderUtil<>("RollCallStatisticCmp.fxml", dto);
            abnormalStudentBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }
}
