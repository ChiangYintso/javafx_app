package main.java.pers.jiangyinzuo.rollcall.ui.console.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.service.TeachingClassService;
import main.java.pers.jiangyinzuo.rollcall.service.Impl.TeachingClassServiceImpl;
import main.java.pers.jiangyinzuo.rollcall.ui.console.AbstractUi;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;
import main.java.pers.jiangyinzuo.rollcall.util.Select;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassUi extends AbstractUi {

	private List<TeachingClass> teachingClassList;
	private TeachingClassService service;

//	public TeachingClassUi() throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
//		this.service = new TeachingClassServiceImpl();
//		this.userInfo = UserInfo.getSingleton();
//		this.teachingClassList = this.service.queryTeachingClassesByStudentId(userInfo.getStudent().getStudentId());
//	}

//	@Override
//	public AbstractMenu showUi() {
//		showTeachingClassList();
//
//		return Select.selectMenu(selectedMenuMap, new String[] { "1. ����", "2. �˳�" });
//	}

	private void showTeachingClassList() {
		if (this.teachingClassList == null || this.teachingClassList.size() == 0) {
			System.out.println("��δѡ��!");
		}
		System.out.println("��ѡ��Ľ�ѧ�����£�");
		for (TeachingClass cls : this.teachingClassList) {
			cls.getTeachingClassInfo();
		}
	}

	/**
	 * ����UI�ķ���
	 *
	 * @return Ҫ��ת��UI, ��Ϊnull���������
	 */
	@Override
	public Class<? extends AbstractUi> run() {
		return null;
	}
}
