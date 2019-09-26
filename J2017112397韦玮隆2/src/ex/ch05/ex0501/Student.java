package ex.ch05.ex0501;

public class Student {
	public String name;
	public int age;
	public int chinese;
	public int math;
	public int english;
	
	public int total()
	{
		return this.chinese + this.math + this.english;
	}
	public int average()
	{
		return (this.chinese + this.math + this.english)/3;
	}
	public void setStudent(String n, int a, int c, int m, int e)
	{
		this.name = n;
		this.age = a;
		this.chinese = c;
		this.math = m;
		this.english = e;
	}
}
