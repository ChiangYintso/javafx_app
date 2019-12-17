package pers.jiangyinzuo.chat.common.javafx.util;

import javafx.fxml.FXMLLoader;
import pers.jiangyinzuo.chat.common.javafx.controller.FxController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jiang Yinzuo
 */
public class FxmlCmpLoaderUtil<T, C extends FxController> {

    private T pane = null;
    private C controller = null;

    public static <T, C extends FxController> T loadFxComponent(String path, String fxmlFileName, Object ...params) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:" + System.getProperty("user.dir") + "\\resources2\\" + path +"\\components\\" + fxmlFileName));
        T pane = fxmlLoader.load();
        C controller = fxmlLoader.getController();
        controller.init(params);
        return pane;
    }

    /**
     * ���� Pane(�Զ������)����Controller��ʵ������, ������controller.init(Object... params)����
     * @param fxmlFileName
     * @param params
     */
    public FxmlCmpLoaderUtil(String path, String fxmlFileName, Object ...params) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(
                    new URL("file:" + System.getProperty("user.dir") + "\\resources2\\" + path +"\\components\\" + fxmlFileName)
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
