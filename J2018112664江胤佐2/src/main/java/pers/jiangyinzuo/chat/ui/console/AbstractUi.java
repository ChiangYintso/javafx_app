package main.java.pers.jiangyinzuo.chat.ui.console;

import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    /**
     * 运行UI的方法
     * @return 要跳转的UI, 若为null则结束程序
     */
    public abstract Class<AbstractUi> run();
}
