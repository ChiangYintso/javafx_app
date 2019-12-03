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
        userIdStr = ConsoleIoHelper.scanner.nextLine();
        System.out.println("��������");
        password = ConsoleIoHelper.scanner.nextLine();
        System.out.println("������������");
        String repeatPassword = ConsoleIoHelper.scanner.nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("�������벻һ��");
        } else {
            Long userId = accountService.register(userIdStr, password);
            if (userId.equals(-1L)) {
                System.out.println("ע��ʧ��");
            } else {
                System.out.println("ע��ɹ�, id��" + userId);
            }
        }
        return LoginUi.class;
    }

    private Class<? extends AbstractUi> login() {
        System.out.println("�����˺�");
        userIdStr = ConsoleIoHelper.scanner.nextLine();
        Long userId;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (Exception e) {
            System.out.println("�˺Ÿ�ʽ����");
            return LoginUi.class;
        }
        System.out.println("��������");
        password = ConsoleIoHelper.scanner.nextLine();
        User user = accountService.login(userId, password);
        if (user != null) {
            UserState.getSingleton().setUser(user);
            return MainBoardUi.class;
        } else {
            System.out.println("��¼ʧ��");
            return LoginUi.class;
        }
    }
}
