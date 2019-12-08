package pers.jiangyinzuo.chat.common.javafx.util;

import javafx.application.Platform;

/**
 * @author Jiang Yinzuo
 */
public class UpdateUiUtil {
    /**
     * ��FXApplicationThread�߳��ڸ���UI
     * @param runnable ����UI�Ĳ���
     */
    public static void updateUi(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }
}
