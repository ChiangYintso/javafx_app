package pers.jiangyinzuo.rollcall.ui.console;

/**
 * @author Jiang Yinzuo
 */
public abstract class AbstractUi {
    /**
     * ����UI�ķ���
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    public abstract Class<? extends AbstractUi> run();
}
