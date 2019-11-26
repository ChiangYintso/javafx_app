package pers.jiangyinzuo.chat.ui.console;

import pers.jiangyinzuo.chat.ui.console.helper.ConsoleIoHelper;

/**
 * ���������ҿ���̨UI�������
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
