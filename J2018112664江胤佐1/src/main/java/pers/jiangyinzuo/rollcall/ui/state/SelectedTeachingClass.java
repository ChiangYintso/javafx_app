package pers.jiangyinzuo.rollcall.ui.state;

import pers.jiangyinzuo.rollcall.entity.TeachingClass;

/**
 * 单例模式 
 * @author Jiang Yinzuo
 * 
 */
public class SelectedTeachingClass {
	private static SelectedTeachingClass singleton;
	
	/**
	 * 当前选中的教学班
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
