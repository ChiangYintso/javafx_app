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
     * ���� Pane(�Զ������)����Controller��ʵ������, ������controller.init(Object... params)����
     * @param fxmlFileName FXML�ļ���
     * @param params  FxController�ӿ� init�����Ĳ����б�
     */
    public FxmlCmpLoaderUtil(String fxmlFileName, Object ...params) {
        FXMLLoader fxmlLoader = null;
        try {
            // �ӳ������еĵ�ǰĿ¼���ؾ�̬fxml��Դ�ļ�
            fxmlLoader = new FXMLLoader(
                    new URL("file:" + System.getProperty("user.dir")
                            + "\\resources1\\components\\" + fxmlFileName)
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            T pane = fxmlLoader.load();
            // ��ȡcontroller
            C controller = fxmlLoader.getController();
            // ��ʼ�����
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
