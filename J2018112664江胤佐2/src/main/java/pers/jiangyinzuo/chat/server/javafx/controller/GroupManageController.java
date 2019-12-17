package pers.jiangyinzuo.chat.server.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pers.jiangyinzuo.chat.common.javafx.CustomAlertBoard;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.server.javafx.controller.components.GroupCmpController;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;

import java.util.List;

public class GroupManageController {

    @FXML
    private VBox groupBox;

    @FXML
    private TextField idText;

    @FXML
    private RadioButton searchByGroupIdRadio;

    @FXML
    private ToggleGroup searchToggle;

    @FXML
    private Button queryBtn;

    @FXML
    private Button showAllBtn;

    private List<Group> groupList;

    @FXML
    void queryByCondition(ActionEvent event) {
        try {
            long id = Long.parseLong(idText.getText());
            addToBox(id);
        } catch (NumberFormatException e) {
            CustomAlertBoard.showAlert("ID∏Ò Ω¥ÌŒÛ");
        }
    }

    @FXML
    void showAll(ActionEvent event) {
        addToBox(-1L);
    }

    @FXML
    public void initialize() {
        GroupService groupService = new GroupServiceImpl();
        groupList = groupService.queryAllGroups();
    }

    private void addToBox(Long id) {
        groupBox.getChildren().clear();
        if (id == -1L) {
            for (Group group : groupList) {
                FxmlCmpLoaderUtil<AnchorPane, GroupCmpController> fxmlCmpLoaderUtil =
                        new FxmlCmpLoaderUtil<>("server", "GroupCmp.fxml", group);
                groupBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
            }
        } else if (searchByGroupIdRadio.isSelected()) {
            for (Group group : groupList) {
                if (group.getGroupId().equals(id)) {
                    FxmlCmpLoaderUtil<AnchorPane, GroupCmpController> fxmlCmpLoaderUtil =
                            new FxmlCmpLoaderUtil<>("server", "GroupCmp.fxml", group);
                    groupBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
                    return;
                }
            }
        } else {
            for (Group group : groupList) {
                if (group.getMaster().getUserId().equals(id)) {
                    FxmlCmpLoaderUtil<AnchorPane, GroupCmpController> fxmlCmpLoaderUtil =
                            new FxmlCmpLoaderUtil<>("server", "GroupCmp.fxml", group);
                    groupBox.getChildren().add(fxmlCmpLoaderUtil.getPane());
                }
            }
        }
    }
}
