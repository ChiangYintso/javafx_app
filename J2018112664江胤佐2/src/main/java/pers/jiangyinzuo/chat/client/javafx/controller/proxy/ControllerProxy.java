package pers.jiangyinzuo.chat.client.javafx.controller.proxy;

import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.ChattingBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller间操作的代理类
 * @author Jiang Yinzuo
 */
public class ControllerProxy {
    /**
     * 主面板Controller
     */
    private static MainBoardController mainBoardController;

    /**
     * 聊天面板Controller
     */
    public static Map<Long, ChattingBoardController> groupChattingBoardController = new HashMap<>(20);

    public static void setMainBoardController(MainBoardController mainBoardController) {
        ControllerProxy.mainBoardController = mainBoardController;
    }

    public static MainBoardController getMainBoardController() {
        return mainBoardController;
    }
}
