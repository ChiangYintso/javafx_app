package pers.jiangyinzuo.rollcall.ui.state;

import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;

/**
 * ����ģʽ 
 * @author Jiang Yinzuo
 * 
 */
public class SelectedTeachingClassState {
	private static SelectedTeachingClassState singleton;
	
	/**
	 * ��ǰѡ�еĽ�ѧ��
	 */
	private TeachingClass cls;

	private SelectedTeachingClassState() {
	}
	
	public static SelectedTeachingClassState getSingleton() {
		if (singleton == null) {
			singleton = new SelectedTeachingClassState();
		}
		return singleton;
	}

	public TeachingClass getCls() {
		return cls;
	}

	public void setCls(TeachingClass cls) {
		this.cls = cls;
	}
}
