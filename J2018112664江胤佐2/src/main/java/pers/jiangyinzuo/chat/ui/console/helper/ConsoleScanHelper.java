package main.java.pers.jiangyinzuo.chat.ui.console.helper;

import java.util.Scanner;

/**
 * @author Jiang Yinzuo
 */
public class ConsoleScanHelper {
    private static Scanner scanner;
    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
