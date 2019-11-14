package ex.ch10.ex1005;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class File4 {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("ȱ���ļ���");
			System.exit(1);
		}
		for (int i = 0; i < args.length; ++i) {
			status(args[i]);
		}
	}

	public static void status(String fileName) throws IOException {
		System.out.println("----" + fileName + "----");
		File f = new File(fileName);
		if (!f.exists()) {
			System.out.println("�ļ�û�ҵ�");
			return;
		}
		System.out.println("�ļ�ȫ��Ϊ" + f.getCanonicalPath());
		String p = f.getParent();
		if (p != null) {
			System.out.println("Parent Directory: " + p);
		}
		if (f.canRead()) {
			System.out.println("file is readable");
		}
		if (f.canWrite()) {
			System.out.println("file is writable");
		}
		Date d = new Date();
		d.setTime(f.lastModified());
		System.out.println("Last modified" + d);
		if (f.isFile()) {
			System.out.println("�ļ���С��" + f.length() + "bytes");
		} else if (f.isDirectory()) {
			System.out.println("��Ŀ¼");
		} else {
			System.out.println("�Ȳ����ļ�Ҳ����Ŀ¼");
		}
	}
}
