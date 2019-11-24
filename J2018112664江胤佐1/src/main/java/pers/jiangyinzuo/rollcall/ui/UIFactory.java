package main.java.pers.jiangyinzuo.rollcall.ui;

import java.lang.reflect.InvocationTargetException;

/**
 * ����ģʽ
 * @author Jiang Yinzuo
 *
 */
public class UIFactory {
	 
	 public AbstractUi buildUI(AbstractMenu uiClassName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		 Class<?> clazz = Class.forName(uiClassName.getMenuClassName());
		 return (AbstractUi) clazz.getDeclaredConstructor().newInstance();
	 }
}
