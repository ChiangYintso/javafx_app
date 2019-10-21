package main.java.pers.jiangyinzuo.rollcall.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;

public abstract class UI {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}
	public abstract AbstractMenu showUI() throws CustomException, FileNotFoundException, IOException;
	
	protected Map<String, AbstractMenu> selectedMenuMap = new HashMap<>();
}
