package ex.ch05.ex0503;

import ex.ch05.ex0501.Student;

public class MyObjectA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		s1.setStudent("����", 13, 80, 90, 85);
		System.out.println("��ã��ҽ�" + s1.name + "��" + "�ҽ���" + s1.age +"��");
		System.out.println("�ҵ��ܷ���" + s1.total());
		System.out.println("�ҵ�ƽ������" + s1.average());
	}

}
