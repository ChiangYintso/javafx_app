package ex.ch03.ex0301;

import java.util.Scanner;

public class IfSentence {
	public static void main(String[] args) {
		int a = 0, b= 0, c = 0, max = 0;
		Scanner reader = new Scanner(System.in);
		System.out.print("�Ӽ��������һ��������");
		a = reader.nextInt();
		System.out.print("�Ӽ�������ڶ���������");
		b = reader.nextInt();
		System.out.print("�Ӽ������������������");
		c = reader.nextInt();
		if (a > b) {
			max = a;
		} else {
			max = b;
		}
		if (c > max) {
			max = c;
		}
		System.out.println("��������:" + max);
		reader.close();
	}
}
