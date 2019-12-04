package pers.jiangyinzuo.chat.client.javafx.controller.proxy;

import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;

/**
 * @author Jiang Yinzuo
 */
public class ControllerProxy {
    private static MainBoardController mainBoardController;

    private static Text onLineCount;

    public static Text getOnLineCount() {
        return onLineCount;
    }

    public static void setMainBoardController(MainBoardController mainBoardController) {
        ControllerProxy.mainBoardController = mainBoardController;
    }

    public static MainBoardController getMainBoardController() {
        return mainBoardController;
    }
}
