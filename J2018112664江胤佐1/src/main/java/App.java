package main.java;

import java.lang.reflect.InvocationTargetException;

import main.java.ui.MainUI;
import main.java.ui.MainUIFactory;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MainUIFactory mainUIFactory = new MainUIFactory();
		MainUI mainUI = mainUIFactory.build();
		mainUI.showLoginUI();
	}
}
