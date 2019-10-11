package main.resource;

/**
 * 本应用程序的配置类
 * @author Jiang Yinzuo
 *
 */
public class Config {
	
	enum UI {
		CONSOLE, JAVA_FX;
	}
	
	/**
	 * UI实现模式, 可根据需要切换
	 */
	public static final UI UI_MODE = UI.CONSOLE;
	
	public static String getUIImplName() {
		switch (UI_MODE) {
		case CONSOLE:
			return "main.java.ui.consoleImpl.MainUIConsoleImpl";
		case JAVA_FX:
			return "main.java.ui.javaFXImpl.MainUIJavaFXImpl";
		default:
			return "";
		}
	}
}
