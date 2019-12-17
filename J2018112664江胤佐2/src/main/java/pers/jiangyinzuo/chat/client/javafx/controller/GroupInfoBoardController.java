package pers.jiangyinzuo.chat.client.javafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pers.jiangyinzuo.chat.client.javafx.controller.components.GroupMemberCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.components.SessionCardCmpController;
import pers.jiangyinzuo.chat.client.javafx.controller.proxy.ControllerProxy;
import pers.jiangyinzuo.chat.client.state.SessionState;
import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.common.javafx.StageManager;
import pers.jiangyinzuo.chat.common.javafx.util.FxmlCmpLoaderUtil;
import pers.jiangyinzuo.chat.domain.dto.GroupMemberDTO;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public class GroupInfoBoardController {

    @FXML
    private ImageView groupAvatar;

    @FXML
    private Pane rootLayout;

    @FXML
    private VBox groupMember;

    @FXML
    private TextField groupNameText;

    @FXML
    private Button quitBtn;

    @FXML
    private Text groupId;

    @FXML
    private Label editLabel;

    @FXML
    private Label quitLabel;

    @FXML
    private TextArea groupIntroText;

    private Group group;

    private User user;

    private GroupService groupService = new GroupServiceImpl();

    private List<GroupMemberDTO> groupMemberList;

    @FXML
    private Button editGroupBtn;

    @FXML
    void editGroup(MouseEvent  event) {
        group.setGroupName(groupNameText.getText());
        group.setGroupIntro(groupIntroText.getText());
        groupService.editGroup(group);
        ControllerProxy.groupChattingBoardController.get(group.getGroupId()).updateChattingBoardInfo();
        ControllerProxy.getMainBoardController().loadTreeView();
    }

    @FXML
    void onQuit(MouseEvent event) {
        if (user.getUserId().equals(group.getMaster().getUserId())) {
            groupService.deleteGroup(group.getGroupId());
        } else {
            groupService.removeGroupMember(user.getUserId(), group.getGroupId());
        }
        ControllerProxy.getMainBoardController().loadTreeView();
        StageManager.groupDeleted(group.getGroupId());
        ((Stage) rootLayout.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        group = (Group) SessionState.getSelectedSession();

        groupNameText.setText(group.getGroupName());
        groupAvatar.setImage(new Image(group.getAvatar()));
        groupId.setText(group.getGroupId().toString());
        groupIntroText.setText(group.getGroupIntro());

        user = UserState.getSingleton().getUser();
        if (!user.getUserId().equals(group.getMaster().getUserId())) {
            groupIntroText.setEditable(false);
            groupNameText.setEditable(false);
            editLabel.setVisible(false);
            editGroupBtn.setVisible(false);
            quitLabel.setText("退出群聊");
        } else {
            quitLabel.setText("解散群聊");
        }

        Long privilege = 0L;

        // 获取群成员列表
        groupMemberList = group.getGroupMemberList();

        for (GroupMemberDTO groupMemberDTO: groupMemberList) {
            if (UserState.getSingleton().getUser().getUserId().equals(groupMemberDTO.getUserId())) {
                privilege = groupMemberDTO.getPrivilege();
                break;
            }
        }
        for (GroupMemberDTO groupMemberDTO : groupMemberList) {
            FxmlCmpLoaderUtil<AnchorPane, GroupMemberCmpController> fxmlCmpLoaderUtil
                    = new FxmlCmpLoaderUtil<>("client", "GroupMemberCmp.fxml", groupMemberDTO, privilege, group, this);
            groupMember.getChildren().add(fxmlCmpLoaderUtil.getPane());
        }
        editGroupBtn.setVisible(user.getUserId().equals(group.getMaster().getUserId()));

    }

    public void remove(GroupMemberCmpController groupMemberCmpPane) {
        groupMember.getChildren().remove(groupMemberCmpPane);
    }
}
