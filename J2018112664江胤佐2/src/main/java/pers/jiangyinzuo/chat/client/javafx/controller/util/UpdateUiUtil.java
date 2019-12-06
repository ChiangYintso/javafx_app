package pers.jiangyinzuo.chat.client.javafx.controller.util;

import javafx.application.Platform;

/**
 * @author Jiang Yinzuo
 */
public class UpdateUiUtil {
    /**
     * 在FXApplicationThread线程内更新UI
     * @param runnable 更新UI的操作
     */
    public static void updateUi(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }
}
