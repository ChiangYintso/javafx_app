package ex.ch06.ex0606;

public class StudentA {
	String strName;
	
	public static void main(String[] args) {
		StudentA a = new StudentA();
		a.setName("张三");
		StudentA b = a;
		b.setName("李四");
		StudentA c = new StudentA();
		c.setName("王五");
		System.out.println("a的名字是 " + a.getName());
		System.out.println("b的名字是 " + b.getName());
		System.out.println("c的名字是 " + c.getName());
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
