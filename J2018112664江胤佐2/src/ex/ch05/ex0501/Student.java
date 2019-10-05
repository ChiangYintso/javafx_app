package ex.ch05.ex0501;

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
}
