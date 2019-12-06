package pers.jiangyinzuo.chat.client.javafx.controller.proxy;

import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;

/**
 * Controller间操作的代理类
 * @author Jiang Yinzuo
 */
public class ControllerProxy {
    /**
     * 主面板Controller
     */
    private static MainBoardController mainBoardController;

    public static void setMainBoardController(MainBoardController mainBoardController) {
        ControllerProxy.mainBoardController = mainBoardController;
    }

    public static MainBoardController getMainBoardController() {
        return mainBoardController;
    }
}
