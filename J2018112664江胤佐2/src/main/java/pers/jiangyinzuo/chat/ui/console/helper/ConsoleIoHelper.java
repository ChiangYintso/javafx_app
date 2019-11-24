package main.java.pers.jiangyinzuo.chat.ui.console.helper;

import java.util.Scanner;

/**
 * @author Jiang Yinzuo
 */
public class ConsoleIoHelper {
    /**
     * 全局控制台输入对象
     */
    public static Scanner scanner = new Scanner(System.in);

    public static int selectMenuItem(String[] menuItems) {
        printMenu(menuItems);
        return scanItem(1, menuItems.length);
    }

    public static int scanItem(int firstItem, int lastItem) {
        String item;
        int result;
        while (true) {
            try {
                item = scanner.nextLine();
                result = Integer.parseInt(item);
                if (firstItem <= result && result <= lastItem) {
                    return result;
                } else {
                    System.out.printf("请输入数字%d - %d\n", firstItem, lastItem);
                }
            } catch (NumberFormatException e) {
                System.out.printf("请输入数字%d - %d\n", firstItem, lastItem);
            }
        }
    }

    public static void printMenu(String[] menuItems) {
        System.out.println("---------------");
        for (String item : menuItems) {
            System.out.println(item);
        }
        System.out.println("---------------");
    }
}
