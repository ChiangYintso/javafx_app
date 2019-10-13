package main.java.pers.jiangyinzuo.rollcall.ui;

import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.ui.UI.MENU;

/**
 * ����ģʽ
 * @author Jiang Yinzuo
 *
 */
public class ConsoleUIFactory implements AbstractUIFactory {
	 
	 @Override
	 public UI buildUI(MENU uiClassName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		 Class<?> clazz = Class.forName(uiClassName.getMenuClassName());
		 return (UI) clazz.getDeclaredConstructor().newInstance();
	 }
}
