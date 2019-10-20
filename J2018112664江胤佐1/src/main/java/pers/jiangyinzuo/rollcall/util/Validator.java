package main.java.pers.jiangyinzuo.rollcall.util;

public interface Validator {
	boolean validate(Class clazz, Object objFromFile, Object... obj);
}
