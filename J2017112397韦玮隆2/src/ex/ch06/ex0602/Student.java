package ex.ch06.ex0602;

public class Student {

	String name;
	int age;
	
	public Student()
	{
		System.out.println("Student()����������");
	}
	
	public Student(String newName)
	{
		this.name = newName;
		System.out.println("Student(String newName)����������");
	}
	
	public Student(String newName, int newAge)
	{
		this.name = newName;
		this.age = newAge;
		System.out.println("Student(String newName, int newAge)����������");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student();
		Student s2 = new Student("����");
		Student s3 = new Student("����",15);
	}

}
