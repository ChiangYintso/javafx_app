package ex.ch10.ex1002;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
	public static void main(String[] args) throws IOException {
		int i;
		FileInputStream fin;
		FileOutputStream fout;
		fin = new FileInputStream("./src/ex/ch10/ex1002/myfile.txt");
		fout = new FileOutputStream("./src/ex/ch10/ex1002/yourfile.txt");
		do {
			i = fin.read();
			if (i != -1) {
				fout.write(i);
			}
		} while (i != -1);
		fin.close();
		fout.close();
	}
}
