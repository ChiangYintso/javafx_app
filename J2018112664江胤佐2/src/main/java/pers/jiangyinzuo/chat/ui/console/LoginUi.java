package main.java.pers.jiangyinzuo.chat.ui.console;

import main.java.pers.jiangyinzuo.chat.common.CustomInfo;
import main.java.pers.jiangyinzuo.chat.service.AccountService;
import main.java.pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import main.java.pers.jiangyinzuo.chat.ui.console.helper.ConsoleIoHelper;

/**
 * @author Jiang Yinzuo
 */
public class LoginUi extends AbstractUi {

    private String username;
    private String password;
    private AccountService accountService = new AccountServiceImpl();

    /**
     * 运行UI的方法
     *
     * @return 要跳转的UI, 若为null则结束程序
     */
    @Override
    public Class<? extends AbstractUi> run() {
        System.out.println("******网络聊天室******");
        int item = ConsoleIoHelper.selectMenuItem(new String[]{"1. 注册", "2. 登录"});

        if (item == 1) {
            return register();
        } else {
            return login();
        }
    }

    private Class<? extends AbstractUi> register() {
        System.out.println("输入账号");
        username = ConsoleIoHelper.scanner.nextLine();
        System.out.println("输入密码");
        password = ConsoleIoHelper.scanner.nextLine();
        System.out.println("重新输入密码");
        String repeatPassword = ConsoleIoHelper.scanner.nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("两次密码不一样");
        } else {
            CustomInfo customInfo = accountService.register(username, password);
            if (customInfo.getStatus() == (short) 200) {
            } else {
                System.out.println(customInfo.getErrInfo());
            }
        }
        return LoginUi.class;
    }

    private Class<? extends AbstractUi> login() {
        System.out.println("输入账号");
        username = ConsoleIoHelper.scanner.nextLine();
        System.out.println("输入密码");
        password = ConsoleIoHelper.scanner.nextLine();
        CustomInfo customInfo = accountService.login(username, password);
        if (customInfo.getStatus() == (short) 200) {
            return MainBoardUi.class;
        } else {
            System.out.println(customInfo.getErrInfo());
            return LoginUi.class;
        }
    }
}
