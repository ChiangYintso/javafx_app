package main.java.pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.service.LoginService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.LoginServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import main.java.pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public class LoginController {
    @FXML
    private Pane layout;
    
	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button loginBtn;

	@FXML
	private RadioButton studentRadio;

	@FXML
	private RadioButton teacherRadio;

	private LoginService loginService;

	@FXML
	void onSelectedStudentRadio(ActionEvent event) {
		teacherRadio.setSelected(false);
	}

	@FXML
	void onSelectedTeacherRadio(ActionEvent event) {
		studentRadio.setSelected(false);
	}

	/**
	 * ��¼
	 * @param event
	 * @throws NumberFormatException
	 * @throws FileNotFoundException
	 * @throws CustomException
	 * @throws IOException
	 */
	@FXML
	void login(ActionEvent event) {
		if (validate()) {
			try {
				if (studentRadio.isSelected()) {
					Student student = loginService.studentLogin(Long.parseLong(usernameField.getText()), passwordField.getText());
					UserInfo.getSingleton().setStudent(student);
					SceneRouter.showStage("ѧ��������", "StudentMainBoard.fxml");
					SceneRouter.closeStage("��¼");
				} else if (teacherRadio.isSelected()) {
					Teacher teacher = loginService.teacherLogin(Long.parseLong(usernameField.getText()), passwordField.getText());
					UserInfo.getSingleton().setTeacher(teacher);
					SceneRouter.showStage("��ʦ������", "TeacherMainBoard.fxml");
					SceneRouter.closeStage("��¼");
				}
			} catch (CustomException e) {
				CustomAlertBoard.showAlert("�˺Ż��������");
			} catch (Exception e) {
				CustomAlertBoard.showAlert("δ֪����");
				e.printStackTrace();
			}

		}
	}

	@FXML
	public void initialize() {
		loginService = new LoginServiceImpl();
		CustomAlertBoard.showAlert("ѧ������ʦ�����˺�123����123456");
	}

	/**
	 * �ͻ��˱���֤
	 * @return
	 */
	private boolean validate() {
		if (!(studentRadio.isSelected() ^ teacherRadio.isSelected())) {
			CustomAlertBoard.showAlert("��ѡ�����");
			return false;
		} else if (usernameField.getText().isBlank()) {
			CustomAlertBoard.showAlert("�˺Ų���Ϊ��");
		} else if (usernameField.getText().isBlank()) {
			CustomAlertBoard.showAlert("���벻��Ϊ��");
		}
		return true;
	}

}
