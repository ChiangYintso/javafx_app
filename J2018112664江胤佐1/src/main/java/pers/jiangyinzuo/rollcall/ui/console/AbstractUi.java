package pers.jiangyinzuo.rollcall.ui.console;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    /**
     * 运行UI的方法
     * @return 要跳转的UI, 若为null则结束程序
     */
    public abstract Class<? extends AbstractUi> run();
}
