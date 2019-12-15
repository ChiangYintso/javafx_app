package pers.jiangyinzuo.rollcall.ui.javafx.controller;

import java.lang.reflect.InvocationTargetException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.service.LoginService;
import pers.jiangyinzuo.rollcall.service.impl.LoginServiceImpl;
import pers.jiangyinzuo.rollcall.ui.javafx.common.CustomAlertBoard;
import pers.jiangyinzuo.rollcall.ui.javafx.router.StageManager;
import pers.jiangyinzuo.rollcall.ui.state.UserState;

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
	 * 登录
	 * @param event
	 * @throws NumberFormatException
	 */
	@FXML
	void login(ActionEvent event) {
		if (validate()) {
			try {
				if (studentRadio.isSelected()) {
					Student student = loginService.studentLogin(Long.parseLong(usernameField.getText()), passwordField.getText());
					if (student == null) {
						CustomAlertBoard.showAlert("账号或密码错误");
						return;
					}
					UserState.getSingleton().setStudent(student);
					StageManager.showStage("学生主界面", "StudentMainBoard.fxml");
					StageManager.closeStage("登录");
				} else if (teacherRadio.isSelected()) {
					Teacher teacher = loginService.teacherLogin(Long.parseLong(usernameField.getText()), passwordField.getText());
					if (teacher == null) {
						CustomAlertBoard.showAlert("账号或密码错误");
						return;
					}
					UserState.getSingleton().setTeacher(teacher);
					StageManager.showStage("教师主界面", "TeacherMainBoard.fxml");
					StageManager.closeStage("登录");
				}
			} catch (Exception e) {
				CustomAlertBoard.showAlert("未知错误");
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void initialize() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		loginService = new LoginServiceImpl();
	}

	/**
	 * 客户端表单验证
	 * @return
	 */
	private boolean validate() {
		if (!(studentRadio.isSelected() ^ teacherRadio.isSelected())) {
			CustomAlertBoard.showAlert("请选择身份");
			return false;
		} else if (usernameField.getText().isBlank()) {
			CustomAlertBoard.showAlert("账号不能为空");
		} else if (usernameField.getText().isBlank()) {
			CustomAlertBoard.showAlert("密码不能为空");
		}
		return true;
	}

}
