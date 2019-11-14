package ex.ch10.ex1008;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class MyRandom {

	public static void main(String[] args) {
		File f = new File("./src/ex/ch10/ex1008/RandomFile.txt");
		try { 
			f.createNewFile();
		} catch (IOException e) {
			System.out.println(e);
		}
		String str = "abcdefghijklmnopqrstuvwxyz";
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(str);
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		int a = -1;
		Scanner reader = new java.util.Scanner(System.in);
		while (a < 0 || a > 25) {
			System.out.println("从键盘输入一个0-25的系数");
			a = reader.nextInt();
		}
		try {
			RandomAccessFile inFile = new RandomAccessFile("./src/ex/ch10/ex1008/RandomFile.txt", "r");
			inFile.seek(a);
			char c = (char)inFile.read();
			inFile.close();
			System.out.println("RandomFile.txt 文件中第" + a + "个字符是" + c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
