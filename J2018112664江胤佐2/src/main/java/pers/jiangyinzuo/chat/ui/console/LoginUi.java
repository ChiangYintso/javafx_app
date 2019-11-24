package main.java.pers.jiangyinzuo.chat.ui.console;

import main.java.pers.jiangyinzuo.chat.ui.console.helper.ConsoleScanHelper;

/**
 * @author Jiang Yinzuo
 */
public class LoginUi extends AbstractUi {

    private String username;
    private String password;

    /**
     * 运行UI的方法
     *
     * @return 要跳转的UI, 若为null则结束程序
     */
    @Override
    public Class<AbstractUi> run() {
        int item = 0;
        System.out.println("0: 注册\n1: 登录");
        item = Integer.parseInt(ConsoleScanHelper.getScanner().nextLine());

        if (item == 1) {
            login();
        } else {
            register();
        }

        return null;
    }

    private void register() {
        System.out.println("输入账号");
        username = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("输入密码");
        password = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("重新输入密码");
        String repeatPassword = ConsoleScanHelper.getScanner().nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("两次密码不一样");
        }
    }

    private void login() {
        System.out.println("输入账号");
        username = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("输入密码");
        password = ConsoleScanHelper.getScanner().nextLine();
    }

    /**
     * 初始化UI
     */
    @Override
    public void init() {

    }
}
