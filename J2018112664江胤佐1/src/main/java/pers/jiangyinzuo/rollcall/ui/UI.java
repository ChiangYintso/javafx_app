package main.java.pers.jiangyinzuo.rollcall.ui;

import java.util.HashMap;
import java.util.Map;

public abstract class UI {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}
	public abstract AbstractMenu showUI();
	
	protected Map<String, AbstractMenu> selectedMenuMap = new HashMap<>();
}
