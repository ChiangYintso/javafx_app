package pers.jiangyinzuo.rollcall.ui.state;

import pers.jiangyinzuo.rollcall.entity.TeachingClass;

/**
 * ����ģʽ 
 * @author Jiang Yinzuo
 * 
 */
public class SelectedTeachingClass {
	private static SelectedTeachingClass singleton;
	
	/**
	 * ��ǰѡ�еĽ�ѧ��
	 */
	private TeachingClass cls;

	private SelectedTeachingClass() {
	}
	
	public static SelectedTeachingClass getSingleton() {
		if (singleton == null) {
			singleton = new SelectedTeachingClass();
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
