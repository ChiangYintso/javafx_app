package main.java.pers.jiangyinzuo.rollcall.ui.console;

import java.io.StreamCorruptedException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.config.Config;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class Main {
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
	 * 入口函数
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
			while (!"exit".equals(uiName.getMenuClassName())) {
				AbstractUi ui = uiFactory.buildUI(uiName);
				uiName = ui.showUi();
			}
		} catch (StreamCorruptedException e) {
			System.out.println("请清空文件");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileHelper.scanner.close();
		}
	}
}
