package pers.jiangyinzuo.rollcall.ui.state;

import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;

/**
 * 单例模式 
 * @author Jiang Yinzuo
 * 
 */
public class SelectedTeachingClassState {
	private static SelectedTeachingClassState singleton;
	
	/**
	 * 当前选中的教学班
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
