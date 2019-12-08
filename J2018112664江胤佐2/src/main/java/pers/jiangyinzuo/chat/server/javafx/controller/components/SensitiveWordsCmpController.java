package pers.jiangyinzuo.chat.server.javafx.controller.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.server.javafx.controller.SensitiveManagerController;
import pers.jiangyinzuo.chat.service.MessageService;
import pers.jiangyinzuo.chat.service.impl.MessageServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class SensitiveWordsCmpController implements FxController {

    @FXML
    private TextField sensitiveWord;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    private String oldWord;

    private Pane self;
    private SensitiveManagerController controllerCallBack;

    @FXML
    void deleteSensitiveWord(ActionEvent event) {
        if (oldWord.equals(sensitiveWord.getText())) {
            messageService.deleteSensitiveWord(oldWord);
            controllerCallBack.removeWordPane(self);
        } else {
            CustomAlertBoard.showAlert("敏感词已被修改, 无法删除");
        }
    }

    private MessageService messageService = new MessageServiceImpl();

    @FXML
    void updateSensitiveWord(ActionEvent event) {
        String newWord = sensitiveWord.getText();
        if (newWord.isBlank()) {
            CustomAlertBoard.showAlert("敏感词不能为空");
            sensitiveWord.setText(oldWord);
        } else if (controllerCallBack.getSensitiveWordList().contains(newWord)) {
            CustomAlertBoard.showAlert("已经有该敏感词");
            sensitiveWord.setText(oldWord);
        } else if (!newWord.equals(oldWord)) {
            messageService.updateSensitiveWord(newWord, oldWord);
            CustomAlertBoard.showAlert("修改成功");
            oldWord = sensitiveWord.getText();
            controllerCallBack.replaceSensitiveWord(oldWord, newWord);
        }
    }

    @Override
    public void init(Object... params) {
        oldWord = (String) params[0];
        sensitiveWord.setText((String) params[0]);
        controllerCallBack = (SensitiveManagerController) params[1];
    }

    public void setSelfPane(Pane pane) {
        self = pane;
    }

    public interface Parent {
        void removeWordPane(Pane pane);
    }
}
