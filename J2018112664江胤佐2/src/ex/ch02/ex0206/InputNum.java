package ex.ch02.ex0206;

import java.util.Scanner;

public class InputNum {
	public static void main(String[] args) {
		int a = 0;
		float b = 0F;
		double c = 0D;
		String s = "";
		Scanner reader = new Scanner(System.in);

		System.out.print("�Ӽ�������һ���ַ�");
		s = reader.nextLine();
		System.out.println("��������ַ�����" + s);

		System.out.print("�Ӽ�������һ������:");

		a = reader.nextInt();

		System.out.println("�������������" + a);
		System.out.print("�Ӽ���������һ�������ȸ�����:");
		b = reader.nextFloat();
		System.out.println("������ĵ����ȸ�������" + b);
		System.out.print("�Ӽ�������һ��˫���ȸ�����:");
		c = reader.nextDouble();
		System.out.println("�������˫���ȸ�������" + c);
		reader.close();
	}
}
