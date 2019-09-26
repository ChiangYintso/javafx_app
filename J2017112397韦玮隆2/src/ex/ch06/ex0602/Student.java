package ex.ch06.ex0602;

public class Student {

	String name;
	int age;
	
	public Student()
	{
		System.out.println("Student()方法被调用");
	}
	
	public Student(String newName)
	{
		this.name = newName;
		System.out.println("Student(String newName)方法被调用");
	}
	
	public Student(String newName, int newAge)
	{
		this.name = newName;
		this.age = newAge;
		System.out.println("Student(String newName, int newAge)方法被调用");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		Student s2 = new Student("张三");
		Student s3 = new Student("李四",15);
	}

}
