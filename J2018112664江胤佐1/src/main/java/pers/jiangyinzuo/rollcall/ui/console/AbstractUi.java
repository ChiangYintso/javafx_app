package pers.jiangyinzuo.rollcall.ui.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    /**
     * 运行UI的方法
     * @return 要跳转的UI, 若为null则结束程序
     */
    public abstract Class<? extends AbstractUi> run() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
