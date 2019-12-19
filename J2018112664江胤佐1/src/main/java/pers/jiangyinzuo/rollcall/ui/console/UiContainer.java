package pers.jiangyinzuo.rollcall.ui.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
public class UiContainer {
    // ��ǰ�������е�UI
    private static AbstractUi currentUi;

    // ����ڵľ�̬main()��������, ÿһ��Ui���н����󣬸�����run()�����ķ��ؽ��������һ��Ui
    static void run(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        currentUi = buildUi(clazz);
        while (clazz != null) {
            clazz = currentUi.run();
            if (clazz != null) {
                currentUi = buildUi(clazz);
            }
        }
    }

    // ���䴴��Ui
    private static AbstractUi buildUi(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (AbstractUi) clazz.getDeclaredConstructor().newInstance();
    }
}
