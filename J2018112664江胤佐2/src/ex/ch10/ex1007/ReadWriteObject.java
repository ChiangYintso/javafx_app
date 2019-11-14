package ex.ch10.ex1007;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class Student implements Serializable {
	String name;
	int age;
	String dept;
	public Student(String name, int age, String dept) {
		super();
		this.name = name;
		this.age = age;
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", dept=" + dept + "]";
	}
	
}

public class ReadWriteObject {

	public static void main(String[] args) {
		Student w1 = new Student("张三", 20, "计算机系");
		Student w2 = new Student("李四", 21, "金融系");
		FileOutputStream fout;
		ObjectOutputStream dout;
		FileInputStream fin;
		ObjectInputStream din;
		File f = new File("./src/ex/ch10/ex1007/ReadWriteObject.txt");
		try {
			f.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fout = new FileOutputStream(f);
			dout = new ObjectOutputStream(fout);
			dout.writeObject(w1);
			dout.writeObject(w2);
			dout.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			fin = new FileInputStream(f);
			din = new ObjectInputStream(fin);
			Student r1 = (Student) din.readObject();
			Student r2 = (Student) din.readObject();
			System.out.println(r1.toString());
			System.out.println(r2.toString());
			din.close();
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
