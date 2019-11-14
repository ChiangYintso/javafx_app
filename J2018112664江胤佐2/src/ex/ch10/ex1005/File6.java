package ex.ch10.ex1005;

import java.io.File;

public class File6 {
	public static void main(String[] args) {
		File target = new File("a.txt");
		if (! target.exists()) {
			System.out.println("文件不存在");
		} else if (target.delete()) {
			System.out.println("文件被删除");
		} else {
			System.out.println("文件不能删除");
		}
	}
}
