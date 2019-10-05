package ex.ch05.ex0503;

public class Student {
	public String name;
	public int age;
	public int chinese;
	public int math;
	public int english;
	public int total() {
		return chinese + math + english;
	}
	public int average() {
		return total() / 3;
	}
	void setStudent(String n, int a, int c, int m, int e) {
		name = n;
		age = a;
		chinese = c;
		math = m;
		english = e;
	}
}
