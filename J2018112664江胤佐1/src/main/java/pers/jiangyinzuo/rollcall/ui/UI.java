package main.java.pers.jiangyinzuo.rollcall.ui;

public interface UI {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}
	AbstractMenu showUI();
}
