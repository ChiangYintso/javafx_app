package main.java.pers.jiangyinzuo.chat.ui.console;

import com.mysql.cj.log.Log;
import main.java.pers.jiangyinzuo.chat.ui.console.helper.ConsoleScanHelper;

/**
 * 网络聊天室控制台UI的入口类
 * @author Jiang Yinzuo
 */
public class Main {
    public static void main(String[] args) {
        try {
            UiContainer.run(LoginUi.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConsoleScanHelper.closeScanner();
        }
    }
}
