package pers.jiangyinzuo.chat.client.console;

import pers.jiangyinzuo.chat.client.state.UserState;
import pers.jiangyinzuo.chat.domain.entity.User;
import pers.jiangyinzuo.chat.service.AccountService;
import pers.jiangyinzuo.chat.service.impl.AccountServiceImpl;
import pers.jiangyinzuo.chat.client.console.helper.ConsoleIoHelper;

/**
 * @author Jiang Yinzuo
 */
public class LoginUi extends AbstractUi {

    private String userIdStr;
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
        userIdStr = ConsoleIoHelper.scanner.nextLine();
        System.out.println("输入密码");
        password = ConsoleIoHelper.scanner.nextLine();
        System.out.println("重新输入密码");
        String repeatPassword = ConsoleIoHelper.scanner.nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("两次密码不一样");
        } else {
            Long userId = accountService.register(userIdStr, password);
            if (userId.equals(-1L)) {
                System.out.println("注册失败");
            } else {
                System.out.println("注册成功, id是" + userId);
            }
        }
        return LoginUi.class;
    }

    private Class<? extends AbstractUi> login() {
        System.out.println("输入账号");
        userIdStr = ConsoleIoHelper.scanner.nextLine();
        Long userId;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (Exception e) {
            System.out.println("账号格式错误");
            return LoginUi.class;
        }
        System.out.println("输入密码");
        password = ConsoleIoHelper.scanner.nextLine();
        User user = accountService.login(userId, password);
        if (user != null) {
            UserState.getSingleton().setUser(user);
            return MainBoardUi.class;
        } else {
            System.out.println("登录失败");
            return LoginUi.class;
        }
    }
}
