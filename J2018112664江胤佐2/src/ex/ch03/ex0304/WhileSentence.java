package ex.ch03.ex0304;

import java.util.Scanner;

public class WhileSentence {

	public static void main(String[] args) {
		int a = 0, i = 1, s = 0;
		Scanner reader = new Scanner(System.in);
		while (a < 50 || a > 100) { 
			System.out.print("�Ӽ�������һ��50~100������");
			a = reader.nextInt();
		}
		System.out.println("�����������" + a);
		while (i <= a) {
			s += i;
			++i;
		}
		System.out.println("1 + 2 + 3 + ... + " + a + " = " + s);
		reader.close();
	}

}
