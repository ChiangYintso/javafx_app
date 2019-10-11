package main.java.ui;

import java.lang.reflect.InvocationTargetException;

import main.resource.Config;

public class MainUIFactory {
	 public MainUI build() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 Class<?> clazz = Class.forName(Config.getUIImplName());
		 MainUI mainUI = (MainUI) clazz.getDeclaredConstructor().newInstance();
		 return mainUI;
	 }
}
