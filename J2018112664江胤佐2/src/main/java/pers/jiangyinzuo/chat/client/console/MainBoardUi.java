package pers.jiangyinzuo.chat.client.console;

import pers.jiangyinzuo.chat.client.console.helper.ConsoleIoHelper;

/**
 * @author Jiang Yinzuo
 */
public class MainBoardUi extends AbstractUi {
    /**
     * ����UI�ķ���
     *
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    @Override
    public Class<AbstractUi> run() {
        ConsoleIoHelper.selectMenuItem(new String[] {"1. ѡ���������", "2. ����"});
        return null;
    }
}
