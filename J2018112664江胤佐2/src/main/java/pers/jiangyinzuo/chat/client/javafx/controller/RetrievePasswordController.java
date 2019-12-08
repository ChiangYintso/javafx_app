package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class RetrievePasswordController {

    private static final String ANSWER = "2018112664";

    @FXML
    private Pane layout;

    @FXML
    private TextField userIdStr;

    @FXML
    private Text label1;

    @FXML
    private Text label2;

    @FXML
    private PasswordField answer;

    @FXML
    private Button queryBtn;

    @FXML
    private Text title;

    private AccountService accountService = new AccountServiceImpl();

    @FXML
    void queryPassword(ActionEvent event) {
        if (!ANSWER.equals(answer.getText().strip())) {
            CustomAlertBoard.showAlert("回答错误，答案是2018112664");
        } else {
            try {
                Long userId = Long.parseLong(userIdStr.getText());
                String password = accountService.retrievePassword(userId);
                if (password == null) {
                    CustomAlertBoard.showAlert("账号不存在");
                } else {
                    CustomAlertBoard.showAlert("密码是: " + password);
                }
            } catch (NumberFormatException e) {
                CustomAlertBoard.showAlert("账号格式错误");
            }
        }
    }

}
