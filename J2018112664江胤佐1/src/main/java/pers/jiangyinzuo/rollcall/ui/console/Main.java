package pers.jiangyinzuo.rollcall.ui.console;

import java.io.StreamCorruptedException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.config.Config;
import pers.jiangyinzuo.rollcall.helper.ConsoleIoHelper;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class Main {
    /**
     * @param args
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public static void main(String[] args) throws IllegalArgumentException,
            SecurityException {
        try {
            UiContainer.run(LoginUi.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConsoleIoHelper.scanner.close();
        }
    }
}
