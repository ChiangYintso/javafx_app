package main.resource;

/**
 * ��Ӧ�ó����������
 * @author Jiang Yinzuo
 *
 */
public class Config {
	
	enum UI {
		CONSOLE, JAVA_FX;
	}
	
	/**
	 * UIʵ��ģʽ, �ɸ�����Ҫ�л�
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
