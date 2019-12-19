package pers.jiangyinzuo.rollcall.ui.console;

import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;

/**
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
