package main.java.pers.jiangyinzuo.rollcall.ui;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.ui.state.UserInfo;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
	static enum UI_IMPLEMENT {
		CONSOLE, JAVA_FX;
	}

	protected UserInfo userInfo;

	/**
	 * ��ʾUI
	 * @return
	 * @throws CustomException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public abstract AbstractMenu showUi() throws CustomException, FileNotFoundException, IOException;
	
	protected Map<String, AbstractMenu> selectedMenuMap = new HashMap<>();

	/**
	 * ���ò˵�ӳ��
	 */
	abstract protected void setSelectedMenuMap();
}
