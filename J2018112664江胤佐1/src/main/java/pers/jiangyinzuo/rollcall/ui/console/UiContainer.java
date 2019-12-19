package pers.jiangyinzuo.rollcall.ui.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
public class UiContainer {
    // 当前正在运行的UI
    private static AbstractUi currentUi;

    // 供入口的静态main()方法调用, 每一个Ui运行结束后，根据其run()方法的返回结果构造下一个Ui
    static void run(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        currentUi = buildUi(clazz);
        while (clazz != null) {
            clazz = currentUi.run();
            if (clazz != null) {
                currentUi = buildUi(clazz);
            }
        }
    }

    // 反射创建Ui
    private static AbstractUi buildUi(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (AbstractUi) clazz.getDeclaredConstructor().newInstance();
    }
}
