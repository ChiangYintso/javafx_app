package ex.ch10.ex1004;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	public static void main(String[] args) throws IOException {
		FileWriter ft = new FileWriter("./src/ex/ch10/ex1004/test.txt");
		String str1 = "西南交通大学";
		String str2 = "欢迎使用java";
		ft.write(str1);
		ft.write(str2);
		ft.close();
	}
}
