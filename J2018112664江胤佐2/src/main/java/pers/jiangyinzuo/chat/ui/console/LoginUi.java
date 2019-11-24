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
     * ����UI�ķ���
     *
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    @Override
    public Class<? extends AbstractUi> run() {
        System.out.println("******����������******");
        int item = ConsoleIoHelper.selectMenuItem(new String[]{"1. ע��", "2. ��¼"});

        if (item == 1) {
            return register();
        } else {
            return login();
        }
    }

    private Class<? extends AbstractUi> register() {
        System.out.println("�����˺�");
        username = ConsoleIoHelper.scanner.nextLine();
        System.out.println("��������");
        password = ConsoleIoHelper.scanner.nextLine();
        System.out.println("������������");
        String repeatPassword = ConsoleIoHelper.scanner.nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("�������벻һ��");
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
        System.out.println("�����˺�");
        username = ConsoleIoHelper.scanner.nextLine();
        System.out.println("��������");
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
