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
     * ����UI�ķ���
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    public abstract AbstractUi run();

    /**
     * ��ʼ��UI
     */
    public abstract void init();
}
