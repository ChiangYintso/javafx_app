package main.java.pers.jiangyinzuo.rollcall.config;

import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.MainUIConsoleImpl;

/**
 * 本应用程序的配置类
 * @author Jiang Yinzuo
 *
 */
public class Config {
	
	/**
	 * UI实现模式, 可根据需要切换
	 */
	public static final String UI_IMPL_CLASS = MainUIConsoleImpl.class.getName();
}
