package pers.jiangyinzuo.rollcall.config;

import pers.jiangyinzuo.rollcall.ui.console.LoginUi;

/**
 * 本应用程序的配置类
 * @author Jiang Yinzuo
 *
 */
public class Config {
	
	/**
	 * 时区
	 */
	public static final int TIME_ZONE = 8;
	
	/**
	 * UI实现模式, 可根据需要切换
	 */
	public static final String UI_IMPL_CLASS = LoginUi.class.getName();
}
