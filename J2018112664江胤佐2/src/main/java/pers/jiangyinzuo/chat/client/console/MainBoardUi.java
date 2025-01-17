package pers.jiangyinzuo.chat.client.console;

import pers.jiangyinzuo.chat.client.console.helper.ConsoleIoHelper;

/**
 * @author Jiang Yinzuo
 */
public class MainBoardUi extends AbstractUi {
    /**
     * 运行UI的方法
     *
     * @return 要跳转的UI, 若为null则结束程序
     */
    @Override
    public Class<AbstractUi> run() {
        ConsoleIoHelper.selectMenuItem(new String[] {"1. 选择聊天对象", "2. 设置"});
        return null;
    }
}
