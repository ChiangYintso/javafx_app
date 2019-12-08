package pers.jiangyinzuo.chat.server.javafx;

import pers.jiangyinzuo.chat.server.javafx.controller.MainBoardController;

/**
 * @author Jiang Yinzuo
 */
public class GuiBroker {

    /**
     * 主面板Controller单例
     */
    private static MainBoardController singleMainBoardController = null;

    public static MainBoardController getSingleMainBoardController() {
        return singleMainBoardController;
    }

    public static void setSingleMainBoardController(MainBoardController singleMainBoardController) {
        if (singleMainBoardController != null) {
            GuiBroker.singleMainBoardController = singleMainBoardController;
        }
    }

    /**
     * 收到来自服务器转发管理类的消息
     * @param message
     * @param userId
     */
    public void onNewMessageArrived(byte[] message, Long userId) {
        singleMainBoardController.addNewToDoItem(message, userId);
    }
}
