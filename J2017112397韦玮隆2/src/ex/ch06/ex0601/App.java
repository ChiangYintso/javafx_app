package ex.ch06.ex0601;

class Person
{
	String name;
	private int age;
	
	public boolean setAge(int newAge)
	{
		if(newAge <= 20 && newAge >= 5)
			return true;
		return false;
	}
	public int getAge()
	{
		return this.age;
	}
}

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person s1 = new Person();
		s1.name = "张三";
		if(s1.setAge(25))
			System.out.println("姓名为：" + s1.name + "  年龄为：" + s1.getAge());
		else
			System.out.println("年龄错误");
	}

}
