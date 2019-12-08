package pers.jiangyinzuo.chat.common.javafx.util;

import javafx.fxml.FXMLLoader;
import pers.jiangyinzuo.chat.client.javafx.controller.FxController;

import java.io.IOException;

/**
 * @author Jiang Yinzuo
 */
public class FxmlCmpLoaderUtil<T, C extends FxController> {

    private T pane = null;
    private C controller = null;

    public static <T, C extends FxController> T loadFxComponent(String path, String fxmlFileName, Object ...params) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FxmlCmpLoaderUtil.class.getResource("../../../" + path + "/javafx/scenes/components/" + fxmlFileName));
        T pane = fxmlLoader.load();
        C controller = fxmlLoader.getController();
        controller.init(params);
        return pane;
    }

    /**
     * 创建 Pane(自定义组件)及其Controller的实例对象, 并调用controller.init(Object... params)方法
     * @param fxmlFileName
     * @param params
     */
    public FxmlCmpLoaderUtil(String path, String fxmlFileName, Object ...params) {
        FXMLLoader fxmlLoader = new FXMLLoader(
                FxmlCmpLoaderUtil.class.getResource(
                        "../../../" + path + "/javafx/scenes/components/" + fxmlFileName
                )
        );
        try {
            T pane = fxmlLoader.load();
            C controller = fxmlLoader.getController();
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
