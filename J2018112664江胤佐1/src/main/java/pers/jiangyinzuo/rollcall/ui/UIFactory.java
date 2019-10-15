package main.java.pers.jiangyinzuo.rollcall.ui;

import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;

/**
 * 工厂模式
 * @author Jiang Yinzuo
 *
 */
public class UIFactory {
	 
	 public UI buildUI(AbstractMenu uiClassName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		 Class<?> clazz = Class.forName(uiClassName.getMenuClassName());
		 return (UI) clazz.getDeclaredConstructor().newInstance();
	 }
}
