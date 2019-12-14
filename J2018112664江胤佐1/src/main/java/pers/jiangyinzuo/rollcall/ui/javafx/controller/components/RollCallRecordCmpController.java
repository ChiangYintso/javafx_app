package pers.jiangyinzuo.rollcall.ui.javafx.controller.components;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;

/**
 * @author Jiang Yinzuo
 */
public class RollCallRecordCmpController implements FxController {

    @FXML
    private Text prescene;

    @FXML
    private Text rollCallType;

    @FXML
    private Text instant;

    @FXML
    private Text className;

    /**
     * ≥ı ºªØ
     *
     * @param params
     */
    @Override
    public void init(Object... params) {
        this.prescene.setText(((RollCall)params[0]).getPresence());
        this.rollCallType.setText(((RollCall)params[0]).getRollCallType());
        this.instant.setText(((RollCall)params[0]).getRollCallTime().toString());
    }
}
