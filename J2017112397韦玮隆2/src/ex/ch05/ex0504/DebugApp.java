package ex.ch05.ex0504;

class Person
{
	String name;
	int age;
	public int biJiao(int a1, int a2)
	{
		if(a1>a2)
			return a1;
		else
			return a2;
	}
}

public class DebugApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 1;
		int y = 2;
		Person p =new Person();
		p.name = "张三";
		p.age = 20;
		int s = p.biJiao(x, y);
		System.out.println("较大的数是" + s);
	}

}
