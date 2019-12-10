package pers.jiangyinzuo.chat.client.javafx.controller.components;

import javafx.event.ActionEvent;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.domain.dto.GroupMemberDTO;

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

    @FXML
    void changePrivilege(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {

    }

    /**
     * 初始化
     *
     * @param params groupMemberDTO, privilege
     */
    @Override
    public void init(Object... params) {
        groupMemberDTO = (GroupMemberDTO) params[0];
        Long privilege = (Long) params[1];
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
