package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.domain.dto.LoginDTO;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.server.javafx.controller.components.LogCmpController;
import pers.jiangyinzuo.chat.server.javafx.controller.components.UserStatusCmpController;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.UserService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import pers.jiangyinzuo.chat.service.impl.UserServiceImpl;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class UserManageController {

    @FXML
    private Tab userStatusPage;

    @FXML
    private VBox userBox;

    @FXML
    private VBox logBox;

    @FXML
    private TextField userIdText;

    @FXML
    private Button searchBtn;

    private UserService userService = new UserServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    @FXML
    void search(ActionEvent event) {
        if (userIdText.getText() == null || userIdText.getText().isBlank()) {
            if (userStatusPage.isSelected()) {
                // 查看用户信息
                addToUserBox(-1L);
            } else {
                CustomAlertBoard.showAlert("ID不能为空");
            }
        } else {
            try {
                long userId = Long.parseLong(userIdText.getText());
                if (userStatusPage.isSelected()) {
                    // 查看用户信息
                    addToUserBox(userId);
                } else {
                    // 查看用户登录日志
                    addToLogBox(userId);
                }
            } catch (NumberFormatException e) {
                CustomAlertBoard.showAlert("ID格式有误");
            }
        }
    }

    private List<User> userList;

    private List<LoginDTO> loginDTOList;


    private void addToLogBox(Long userId) {
        logBox.getChildren().clear();
        loginDTOList = accountService.queryLogsByUserId(userId);
        for (LoginDTO dto : loginDTOList) {
            FxmlCmpLoaderUtil<AnchorPane, LogCmpController> fxmlCmpLoaderUtil
                    = new FxmlCmpLoaderUtil<>("server", "LogCmp.fxml", dto);
            logBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }

    @FXML
    public void initialize() {
        userList = userService.queryAllUsers();
        addToUserBox(-1L);
    }

    private void addToUserBox(Long userId) {
        userBox.getChildren().clear();
        if (userId != -1L) {
            for (User user : userList) {
                if (user.getUserId().equals(userId)) {
                    FxmlCmpLoaderUtil<AnchorPane, UserStatusCmpController> fxmlCmpLoaderUtil =
                            new FxmlCmpLoaderUtil<>("server", "UserStatusCmp.fxml", user);
                    userBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
                    return;
                }
            }
            CustomAlertBoard.showAlert("用户不存在");
            return;
        }

        for (User user : userList) {
            FxmlCmpLoaderUtil<AnchorPane, UserStatusCmpController> fxmlCmpLoaderUtil =
                    new FxmlCmpLoaderUtil<>("server", "UserStatusCmp.fxml", user);
            userBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
    }
}
