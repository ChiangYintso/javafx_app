package main.java.pers.jiangyinzuo.chat.ui.console;

import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    protected Map<String, Class> menu;

    protected void addMenu(String menuItem, Class clazz) {
        menu.put(menuItem, clazz);
    }

    protected void addMenu(String[] menuItems, Class[] classes) {
        for (int i = 0; i < menuItems.length; ++i) {
            menu.put(menuItems[i], classes[i]);
        }
    }

    /**
     * 运行UI的方法
     * @return 要跳转的UI, 若为null则结束程序
     */
    public abstract AbstractUi run();

    /**
     * 初始化UI
     */
    public abstract void init();
}
