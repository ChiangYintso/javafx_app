package main.java.pers.jiangyinzuo.rollcall.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

public abstract class UI {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}
	
	protected UserInfo userInfo;
	
	public abstract AbstractMenu showUI() throws CustomException, FileNotFoundException, IOException;
	
	protected Map<String, AbstractMenu> selectedMenuMap = new HashMap<>();
	
	abstract protected void setSelectedMenuMap();
}
