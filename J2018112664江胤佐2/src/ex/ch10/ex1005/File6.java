package ex.ch10.ex1005;

import java.io.File;

public class File6 {
	public static void main(String[] args) {
		File target = new File("a.txt");
		if (! target.exists()) {
			System.out.println("�ļ�������");
		} else if (target.delete()) {
			System.out.println("�ļ���ɾ��");
		} else {
			System.out.println("�ļ�����ɾ��");
		}
	}
}
