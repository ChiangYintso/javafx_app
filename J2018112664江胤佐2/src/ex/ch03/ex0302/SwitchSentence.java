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
			System.out.println("������������");
			break;
		case 1:
			System.out.println("����������һ");
			break;
		case 2:
			System.out.println("���������ڶ�");
			break;
		case 3:
			System.out.println("������������");
			break;
		case 4:
			System.out.println("������������");
			break;
		case 5:
			System.out.println("������������");
			break;
		case 6:
			System.out.println("������������");
			break;
		default:
			System.out.println("�������");
			break;
		}
		reader.close();
	}
}
