package pers.jiangyinzuo.chat.client.javafx.controller.components;

import javafx.event.ActionEvent;
import pers.jiangyinzuo.chat.client.javafx.controller.GroupInfoBoardController;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.domain.dto.GroupMemberDTO;
import pers.jiangyinzuo.chat.domain.entity.Group;
import pers.jiangyinzuo.chat.service.GroupService;
import pers.jiangyinzuo.chat.service.impl.GroupServiceImpl;

/**
 * @author Jiang Yinzuo
 */
public class GroupMemberCmpController implements FxController {

    @FXML
    private Text userIdText;

    @FXML
    private Text userNameText;

    @FXML
    private Text privilegeText;

    @FXML
    private Button changePrivilegeBtn;

    @FXML
    private Button quitBtn;

    private GroupMemberDTO groupMemberDTO;

    private Group group;

    private GroupInfoBoardController controllerCallBack;

    private GroupService groupService = new GroupServiceImpl();

    @FXML
    void changePrivilege(ActionEvent event) {
        long newPrivilege;
        if (groupMemberDTO.getPrivilege() == 2L) {
            newPrivilege = 1L;
            this.changePrivilegeBtn.setText("成为管理员");
            privilegeText.setText("群成员");
        } else {
            newPrivilege = 2L;
            this.changePrivilegeBtn.setText("取消管理员");
            privilegeText.setText("管理员");
        }
        groupService.changeMemberPrivilege(groupMemberDTO.getUserId(), newPrivilege, group.getGroupId());
        groupMemberDTO.setPrivilege(newPrivilege);
    }

    @FXML
    void quit(ActionEvent event) {
        groupService.removeGroupMember(groupMemberDTO.getUserId(), group.getGroupId());
        controllerCallBack.remove(this);
    }

    /**
     * 初始化
     *
     * @param params groupMemberDTO, privilege, group, callback
     */
    @Override
    public void init(Object... params) {
        groupMemberDTO = (GroupMemberDTO) params[0];
        Long privilege = (Long) params[1];
        group = (Group) params[2];
        controllerCallBack = (GroupInfoBoardController) params[3];
        if (privilege == 1L) {
            quitBtn.setVisible(false);
            changePrivilegeBtn.setVisible(false);
        } else if (privilege == 2L) {
            changePrivilegeBtn.setVisible(false);
        }
        if (groupMemberDTO.getPrivilege() == 3L) {
            changePrivilegeBtn.setVisible(false);
            quitBtn.setVisible(false);
        }
        userIdText.setText(groupMemberDTO.getUserId().toString());
        userNameText.setText(groupMemberDTO.getUserName());

        if (groupMemberDTO.getPrivilege() == 3L) {
            privilegeText.setText("群主");
        } else if (groupMemberDTO.getPrivilege() == 2L) {
            privilegeText.setText("管理员");
            changePrivilegeBtn.setText("取消管理员");
        } else {
            privilegeText.setText("群成员");
            changePrivilegeBtn.setText("成为管理员");
        }
    }
}
