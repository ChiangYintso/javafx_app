package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.dto.StudentRollCallResultDTO;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;

public class RollCallStatisticCmpController implements FxController {

    @FXML
    private Text studentIdText;

    @FXML
    private Text studentNameText;

    @FXML
    private Text presenceCount;

    @FXML
    private Text absentCountText;

    @FXML
    private Text lateCountText;

    @FXML
    private Text leaveEarlyText;

    @FXML
    private Text askForLeaveCount;

    @FXML
    private Text abnormalCountText;

    @Override
    public void init(Object... params) {
        StudentRollCallResultDTO dto = (StudentRollCallResultDTO) params[0];
        studentIdText.setText(dto.getStudentId().toString());
        studentNameText.setText(dto.getStudentName());
        absentCountText.setText(dto.getAbsentCount().toString());
        abnormalCountText.setText(dto.getAbnormalCount().toString());
        presenceCount.setText(dto.getPresenceCount().toString());
        askForLeaveCount.setText(dto.getAskForLeaveCount().toString());
        leaveEarlyText.setText(dto.getLeaveEarlyCount().toString());
        lateCountText.setText(dto.getLateCount().toString());
    }
}
