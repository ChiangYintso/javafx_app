package ex.ch10.ex1001;

import java.io.FileInputStream;
import java.io.IOException;

public class ShowFile {
	public static void main(String[] args) throws IOException {
		int i;
		FileInputStream fin;
		fin = new FileInputStream("./src/ex/ch10/ex1001/myfile.txt");
		do {
			i = fin.read();
			if (i != -1) {
				System.out.print((char) i);
			}
		} while (i != -1);
		fin.close();
	}
}
