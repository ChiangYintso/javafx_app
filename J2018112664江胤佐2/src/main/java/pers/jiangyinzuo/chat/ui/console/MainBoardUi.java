package main.java.pers.jiangyinzuo.chat.ui.console;

import main.java.pers.jiangyinzuo.chat.ui.console.helper.ConsoleIoHelper;

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
