package ex.ch06.ex0601;

public class App {
	public static void main(String[] args) {
		Person s1 = new Person();
		s1.name = "ÕÅÈı";
		if (s1.setAge(25)) {
			System.out.println(s1.name + s1.getAge());
		} else {
			System.out.println("ÄêÁä´íÎó");
		}
	}
}

class Person {
	String name;
	private int age;
	public boolean setAge(int newAge) {
		if (5 <= newAge && newAge <= 20) {
			return true;
		} else {
			return false;
		}
	}
	public int getAge() {
		return age;
	}
}
