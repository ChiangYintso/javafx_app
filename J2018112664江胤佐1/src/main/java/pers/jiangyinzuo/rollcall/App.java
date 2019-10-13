package main.java.pers.jiangyinzuo.rollcall;

import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import static main.java.pers.jiangyinzuo.rollcall.ui.UI.MENU;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractUIFactory;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		AbstractUIFactory uiFactory = AbstractUIFactory.getUIFactory();
		MENU uiName = MENU.MAIN;
		while (uiName != MENU.EXIT) {
			UI ui = uiFactory.buildUI(uiName);
			uiName = ui.showUI();
		}
	}
}
