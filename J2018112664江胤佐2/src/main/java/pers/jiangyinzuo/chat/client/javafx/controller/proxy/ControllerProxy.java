package pers.jiangyinzuo.chat.client.javafx.controller.proxy;

import javafx.scene.text.Text;
import pers.jiangyinzuo.chat.client.javafx.controller.ChattingBoardController;
import pers.jiangyinzuo.chat.client.javafx.controller.MainBoardController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller������Ĵ�����
 * @author Jiang Yinzuo
 */
public class ControllerProxy {
    /**
     * �����Controller
     */
    private static MainBoardController mainBoardController;

    /**
     * �������Controller
     */
    public static Map<Long, ChattingBoardController> groupChattingBoardController = new HashMap<>(20);

    public static void setMainBoardController(MainBoardController mainBoardController) {
        ControllerProxy.mainBoardController = mainBoardController;
    }

    public static MainBoardController getMainBoardController() {
        return mainBoardController;
    }
}
