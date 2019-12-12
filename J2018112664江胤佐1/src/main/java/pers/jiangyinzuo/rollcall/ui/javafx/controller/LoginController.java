package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.impl.LoginServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.router.SceneRouter;
import pers.jiangyinzuo.rollcall.ui.state.UserInfo;

/**
 * @author Jiang Yinzuo
 */
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
					if (student == null) {
						CustomAlertBoard.showAlert("�˺Ż��������");
						return;
					}
					UserInfo.getSingleton().setStudent(student);
					SceneRouter.showStage("ѧ��������", "StudentMainBoard.fxml");
					SceneRouter.closeStage("��¼");
				} else if (teacherRadio.isSelected()) {
					Teacher teacher = loginService.teacherLogin(Long.parseLong(usernameField.getText()), passwordField.getText());
					if (teacher == null) {
						CustomAlertBoard.showAlert("�˺Ż��������");
						return;
					}
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
	public void initialize() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		loginService = new LoginServiceImpl();
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
