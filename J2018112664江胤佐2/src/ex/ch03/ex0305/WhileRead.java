package ex.ch03.ex0305;

import java.io.IOException;

public class WhileRead {

	public static void main(String[] args) throws IOException {
		int count = 0, b;
		System.out.print("���������ݣ�");
		while((char)(b = System.in.read()) != '\r') {
			System.out.print((char)b);
			count++;
		}
		System.out.print("\n��������" + count + "���ַ�");
	}

}
