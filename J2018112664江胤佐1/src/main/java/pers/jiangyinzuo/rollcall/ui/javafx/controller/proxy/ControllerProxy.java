package pers.jiangyinzuo.rollcall.ui.javafx.controller.proxy;

import pers.jiangyinzuo.rollcall.ui.javafx.controller.TeacherMainBoardController;

public class ControllerProxy {
    private static TeacherMainBoardController teacherMainBoardController;

    public static void setTeacherMainBoardController(TeacherMainBoardController teacherMainBoardController) {
        ControllerProxy.teacherMainBoardController = teacherMainBoardController;
    }

    public static TeacherMainBoardController getTeacherMainBoardController() {
        return ControllerProxy.teacherMainBoardController;
    }
}
