package pers.jiangyinzuo.chat.server.javafx;

import pers.jiangyinzuo.chat.server.javafx.controller.MainBoardController;

/**
 * @author Jiang Yinzuo
 */
public class GuiBroker {

    /**
     * �����Controller����
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
     * �յ����Է�����ת�����������Ϣ
     * @param message
     * @param userId
     */
    public void onNewMessageArrived(byte[] message, Long userId) {
        singleMainBoardController.addNewToDoItem(message, userId);
    }
}
