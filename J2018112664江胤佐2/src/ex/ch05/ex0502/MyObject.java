package ex.ch05.ex0502;

import ex.ch05.ex0501.Student;

public class MyObject {
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.name = "����";
		s1.age = 12;
		s1.chinese = 80;
		s1.math = 90;
		s1.english = 85;
		System.out.println("��ã��ҽ�" + s1.name + "," + "�ҽ���" + s1.age + "��");
		System.out.println("�ҵ��ܷ���" + s1.total());
		System.out.println("�ҵ�ƽ������" + s1.average());
		Student s2 = new Student();
		s2.name = "����";
		s2.age = 12;
		s2.chinese = 90;
		s2.english = 90;
		s2.math = 90;
		System.out.println(s2.name + " " + s2.age);
		System.out.println(s2.average());
		System.out.println(s2.total());
		if (s1.total() > s2.total()) {
			System.out.println(s1.name + "�ĳɼ���");
		} else if (s2.total() > s1.total()) {
			System.out.println(s2.name + "�ĳɼ���");
		} else {
			System.out.println(s1.name + "��" + s2.name + "�ĳɼ�һ����");
		}
	}
}
