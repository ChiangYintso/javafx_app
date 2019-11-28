package pers.jiangyinzuo.rollcall.ui.console;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    /**
     * ����UI�ķ���
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    public abstract Class<? extends AbstractUi> run() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
}
