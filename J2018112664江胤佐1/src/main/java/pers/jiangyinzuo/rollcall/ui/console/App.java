package main.java.pers.jiangyinzuo.rollcall.ui.console;

import java.io.StreamCorruptedException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.config.Config;
import main.java.pers.jiangyinzuo.rollcall.ui.Ui;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UIFactory;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

/**
 * @author Jiang Yinzuo
 */
public class App {
	private static enum MENU implements AbstractMenu {
		MAIN(Config.UI_IMPL_CLASS);

		private String menuClassName;

		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}

		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}

	}

	/**
	 * ��ں���
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws CustomException
	 */
	public static void main(String[] args)
			throws IllegalArgumentException,
			SecurityException {
		try {
			UIFactory uiFactory = new UIFactory();
			AbstractMenu uiName = MENU.MAIN;
			while (!uiName.getMenuClassName().equals("exit")) {
				Ui ui = uiFactory.buildUI(uiName);
				uiName = ui.showUi();
			}
		} catch (StreamCorruptedException e) {
			System.out.println("������ļ�");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AppFile.scanner.close();
		}
	}
}
