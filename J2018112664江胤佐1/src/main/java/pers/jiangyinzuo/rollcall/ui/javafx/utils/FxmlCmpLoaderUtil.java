package pers.jiangyinzuo.rollcall.ui.javafx.utils;

import javafx.fxml.FXMLLoader;
import pers.jiangyinzuo.rollcall.ui.javafx.controller.FxController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jiang Yinzuo
 */
public class FxmlCmpLoaderUtil<T, C extends FxController> {

    private T pane = null;
    private C controller = null;

    /**
     * 创建 Pane(自定义组件)及其Controller的实例对象, 并调用controller.init(Object... params)方法
     * @param fxmlFileName FXML文件名
     * @param params  FxController接口 init方法的参数列表
     */
    public FxmlCmpLoaderUtil(String fxmlFileName, Object ...params) {
        FXMLLoader fxmlLoader = null;
        try {
            // 从程序运行的当前目录加载静态fxml资源文件
            fxmlLoader = new FXMLLoader(
                    new URL("file:" + System.getProperty("user.dir")
                            + "\\resources1\\components\\" + fxmlFileName)
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            T pane = fxmlLoader.load();
            // 获取controller
            C controller = fxmlLoader.getController();
            // 初始化面板
            controller.init(params);
            this.pane = pane;
            this.controller = controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public C getController() {
        return controller;
    }

    public void setController(C controller) {
        this.controller = controller;
    }

    public T getPane() {
        return pane;
    }

    public void setPane(T pane) {
        this.pane = pane;
    }
}
