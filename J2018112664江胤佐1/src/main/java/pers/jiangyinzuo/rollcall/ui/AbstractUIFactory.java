package main.java.pers.jiangyinzuo.rollcall.ui;

import static main.java.pers.jiangyinzuo.rollcall.config.Config.UI_IMPL;

import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.ui.UI.MENU;

public interface AbstractUIFactory {
	UI buildUI(MENU uiClassName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException;
	
	static AbstractUIFactory getUIFactory() {
		switch (UI_IMPL) {
		case CONSOLE:
			return new ConsoleUIFactory();
		default:
			return new ConsoleUIFactory();
		}
	}
}
