package pers.jiangyinzuo.chat.client.console;

import pers.jiangyinzuo.chat.client.console.helper.ConsoleIoHelper;

/**
 * 网络聊天室控制台UI的入口类
 * @author Jiang Yinzuo
 */
public class Main {
    public static void main(String[] args) {
        try {
            UiContainer.run(LoginUi.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConsoleIoHelper.scanner.close();
        }
    }
}
