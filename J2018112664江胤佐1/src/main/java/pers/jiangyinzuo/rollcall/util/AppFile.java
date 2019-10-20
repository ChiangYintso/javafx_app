package main.java.pers.jiangyinzuo.rollcall.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import main.java.pers.jiangyinzuo.rollcall.util.Validator;
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

	public static String getLine(BufferedReader br) throws IOException {
		return br.readLine();
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
				fieldName = field.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				getter = clazz.getMethod("get" + fieldName);

				if (type.startsWith("class")) {
					tempStr.append(getter.invoke(obj).toString());
				} else {
					System.out.println(getter.invoke(obj));
					tempStr.append("[]");
				}
				tempStr.append(",");
			}
			tempStr.append("\n");
			fileWriter.write(tempStr.toString());
		}
	}

	public static void writeSerializableEntity(Object obj, String fileSuffix) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(AppFile.getAppPath() + fileSuffix, true);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
			objectOutputStream.writeObject(obj);
			System.out.println("写入成功");
		}
	}

	public static Object readSerializableEntity(String fileSuffix, Validator query, Class clazz, Object ...obj)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		try (FileInputStream fileInputStream = new FileInputStream(new File(AppFile.getAppPath() + fileSuffix));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			Object tempObj;
			if (query == null) {
				return objectInputStream.readObject();
			}
			while ((tempObj = objectInputStream.readObject()) != null) {
				if (query.validate(clazz, tempObj, obj)) {
					return tempObj;
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println("未找到记录");
			return null;
		}
	}

	public static void main(String[] main) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		Student student = new Student(1234, "男", "jyz", "软件2018-01班", "123456", "软件工程", new ArrayList<>(),
				new ArrayList<>(), new ArrayList<>());
		AppFile.writeSerializableEntity(student, "student.txt");
		Student result = (Student) AppFile.readSerializableEntity("student.txt", null, student.getClass());
		System.out.println(result.getStudentName());
	}
}
