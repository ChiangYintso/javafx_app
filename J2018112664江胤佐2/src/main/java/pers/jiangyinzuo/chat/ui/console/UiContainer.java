package main.java.pers.jiangyinzuo.chat.ui.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
public class UiContainer {
    private static AbstractUi currentUi;

    public static void run(AbstractUi primaryUi) {

    }
    public static AbstractUi buildUi(Class<AbstractUi> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (AbstractUi) clazz.getDeclaredConstructor().newInstance();
    }
}
