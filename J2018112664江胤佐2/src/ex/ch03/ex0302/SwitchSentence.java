package ex.ch03.ex0302;

import java.util.Scanner;

public class SwitchSentence {
	public static void main(String[] args) {
		int a = -1;
		Scanner reader = new Scanner(System.in);
		do {
			a = reader.nextInt();
		} while (a < 0 || a > 6);
		
		switch(a) {
		case 0:
			System.out.println("今天是星期日");
			break;
		case 1:
			System.out.println("今天是星期一");
			break;
		case 2:
			System.out.println("今天是星期二");
			break;
		case 3:
			System.out.println("今天是星期三");
			break;
		case 4:
			System.out.println("今天是星期四");
			break;
		case 5:
			System.out.println("今天是星期五");
			break;
		case 6:
			System.out.println("今天是星期六");
			break;
		default:
			System.out.println("输入错误");
			break;
		}
		reader.close();
	}
}
