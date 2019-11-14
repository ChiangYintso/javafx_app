package ex.ch10.ex1005;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File2 {
	public static void main(String[] args) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("./src/ex/ch10/ex1005/a.txt"));
		out.write("SWJTU");
		out.newLine();
		out.write("Java≥Ã–Ú…Ëº∆");
		out.flush();
		out.close();
	}
}
