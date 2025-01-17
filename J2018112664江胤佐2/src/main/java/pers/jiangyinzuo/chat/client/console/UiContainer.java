package pers.jiangyinzuo.chat.client.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
class UiContainer {
    private static AbstractUi currentUi;

    static void run(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        currentUi = buildUi(clazz);
        while (clazz != null) {
            clazz = currentUi.run();
            if (clazz != null) {
                currentUi = buildUi(clazz);
            }
        }
    }

    private static AbstractUi buildUi(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (AbstractUi) clazz.getDeclaredConstructor().newInstance();
    }
}
