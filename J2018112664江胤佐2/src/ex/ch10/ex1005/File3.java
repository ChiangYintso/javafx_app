package ex.ch10.ex1005;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class File3 {
	public static void main(String[] args) throws IOException {
		String thisLine;
		BufferedReader in = new BufferedReader(new FileReader("./src/ex/ch10/ex1005/a.txt"));
		while ( (thisLine = in.readLine()) != null) {
			System.out.println(thisLine);
		}
		in.close();
	}
}
