package ex.ch06.ex0606;

public class StudentA {
	String strName;
	
	public static void main(String[] args) {
		StudentA a = new StudentA();
		a.setName("����");
		StudentA b = a;
		b.setName("����");
		StudentA c = new StudentA();
		c.setName("����");
		System.out.println("a�������� " + a.getName());
		System.out.println("b�������� " + b.getName());
		System.out.println("c�������� " + c.getName());
		System.out.println(a == b);
		System.out.println(a == c);
	}

	public String getName() {
		return strName;
	}

	public void setName(String strName) {
		this.strName = strName;
	}
}
