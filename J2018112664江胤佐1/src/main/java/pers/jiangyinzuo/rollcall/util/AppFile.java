package main.java.pers.jiangyinzuo.rollcall.util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import main.java.pers.jiangyinzuo.rollcall.entity.Student;

public class AppFile {
	public static String getAppPath() {
		return "D:\\stuspace\\java2019a\\J2018112664江胤佐1\\src\\main\\files\\";
	}

	public static StringBuilder[] parseLine(StringBuilder line, int wordCount) {
		StringBuilder[] result = new StringBuilder[wordCount];
		for (StringBuilder s : result) {
			s = new StringBuilder();
		}
		int idx = 0;
		for (int i = 0; i < line.length(); ++i) {
			if (line.charAt(i) != ',') {
				result[idx].append(line.charAt(i));
			} else {
				++idx;
			}
		}
		return result;
	}

	public static StringBuilder getLine(FileInputStream fileInputStream) throws IOException {
		StringBuilder result = new StringBuilder();
		char ch;
		while (fileInputStream.available() > 0) {
			ch = (char) fileInputStream.read();
			if (ch == ';') {
				break;
			} else if (ch == '\r' || ch == '\n') {
				continue;
			} else {
				result.append(ch);
			}
		}
		return result;
	}

	public static void writeEntity(Object obj) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class clazz = obj.getClass();
		Field[] classField = clazz.getDeclaredFields();
		String fieldName;
		Method getter;
		String type;

		StringBuilder tempStr = new StringBuilder();
		try (FileWriter fileWriter = new FileWriter(AppFile.getAppPath() + "student.txt", true);) {
			for (Field field : classField) {
				type = field.getGenericType().toString();
				if (type.startsWith("class")) {
					fieldName = field.getName();
					fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					getter = clazz.getMethod("get" + fieldName);
					tempStr.append(getter.invoke(obj).toString());
					tempStr.append(",");
				}
			}
			tempStr.append(";");
			fileWriter.write(tempStr.toString());
		}
	}

	public static void main(String[] main) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeEntity(new Student(123, "男", "jyz", "软件2018-01班", "123456", "软件工程"));
	}
}
