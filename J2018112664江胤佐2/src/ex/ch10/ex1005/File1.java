package ex.ch10.ex1005;

import java.io.File;
import java.io.IOException;

public class File1 {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("没有需要创建的文件");
			System.exit(1);
		} else {
			for (int i = 0; i < args.length; ++i) {
				new File(args[i]).createNewFile();
			}
		}
	}
}
