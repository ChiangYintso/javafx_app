package main.java.pers.jiangyinzuo.chat.ui.console;

import main.java.pers.jiangyinzuo.chat.ui.console.helper.ConsoleScanHelper;

/**
 * @author Jiang Yinzuo
 */
public class LoginUi extends AbstractUi {

    private String username;
    private String password;

    /**
     * ����UI�ķ���
     *
     * @return Ҫ��ת��UI, ��Ϊnull���������
     */
    @Override
    public Class<AbstractUi> run() {
        int item = 0;
        System.out.println("0: ע��\n1: ��¼");
        item = Integer.parseInt(ConsoleScanHelper.getScanner().nextLine());

        if (item == 1) {
            login();
        } else {
            register();
        }

        return null;
    }

    private void register() {
        System.out.println("�����˺�");
        username = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("��������");
        password = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("������������");
        String repeatPassword = ConsoleScanHelper.getScanner().nextLine();
        if (!repeatPassword.equals(password)) {
            System.out.println("�������벻һ��");
        }
    }

    private void login() {
        System.out.println("�����˺�");
        username = ConsoleScanHelper.getScanner().nextLine();
        System.out.println("��������");
        password = ConsoleScanHelper.getScanner().nextLine();
    }

    /**
     * ��ʼ��UI
     */
    @Override
    public void init() {

    }
}
