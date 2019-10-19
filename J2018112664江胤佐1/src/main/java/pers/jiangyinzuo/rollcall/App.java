package main.java.pers.jiangyinzuo.rollcall;

import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.config.Config;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UIFactory;


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
	 * Èë¿Úº¯Êý
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws CustomException 
	 */
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, CustomException {
		try {
			UIFactory uiFactory = new UIFactory();
			AbstractMenu uiName = MENU.MAIN;
			while (!uiName.getMenuClassName().equals("exit")) {
				UI ui = uiFactory.buildUI(uiName);
				uiName = ui.showUI();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
