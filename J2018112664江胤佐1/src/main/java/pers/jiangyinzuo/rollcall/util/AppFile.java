package main.java.pers.jiangyinzuo.rollcall.util;

import java.io.BufferedReader;
import java.io.EOFException;
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
import java.util.List;
import java.util.Scanner;

import main.java.pers.jiangyinzuo.rollcall.service.validator.Validator;
import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.entity.Student;

/**
 * 文件读写类, 负责程序的IO操作
 * 
 * @author Jiang Yinzuo
 *
 */
public class AppFile {
	/**
	 * 全局控制台输入对象
	 */
	public static Scanner scanner = new Scanner(System.in);

	public static int scanItem(int firstItem, int lastItem) {
		String item;
		int result;
		while (true) {
			try {
				item = scanner.nextLine();
				result = Integer.parseInt(item);
				if (firstItem <= result && result <= lastItem) {
					return result;
				} else {
					System.out.printf("请输入数字%d - %d\n", firstItem, lastItem);
				}
			} catch (NumberFormatException e) {
				System.out.printf("请输入数字%d - %d\n", firstItem, lastItem);
			}
		}
	}

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

	public static void writeEntity(Object obj, String fileName) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class clazz = obj.getClass();
		Field[] classField = clazz.getDeclaredFields();
		String fieldName;
		Method getter;
		String type;

		StringBuilder tempStr = new StringBuilder();
		try (FileWriter fileWriter = new FileWriter(AppFile.getAppPath() + fileName, true);) {
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

	public static Object readSerializableEntity(String fileSuffix, Validator query, Class clazz, Object... obj)
			throws FileNotFoundException, IOException, ClassNotFoundException, CustomException {
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
		} catch (EOFException e) {
			throw new CustomException("entity not found", false);
		} catch (CustomException e) {
			System.out.println(e.getErrInfo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> readSerializableEntities(String fileSuffix, Validator v, Object obj)
			throws CustomException, IOException, ClassNotFoundException {
		List<T> arrayList = new ArrayList<>();

		if (v == null) {
			return arrayList;
		}
		Object objFromFile;
		FileInputStream fileInputStream = new FileInputStream(new File(AppFile.getAppPath() + fileSuffix));
		while (fileInputStream.available() > 0) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			objFromFile = objectInputStream.readObject();
			if (v.validate(objFromFile, obj)) {
				arrayList.add((T) objFromFile);
			}

		}
		return arrayList;
	}

	public static <T> void bulkInsertSerializableEntities(String fileSuffix, List<T> objectList) throws IOException {
		if (objectList != null) {
			for (Object obj : objectList) {
				try (FileOutputStream fileOutputStream = new FileOutputStream(AppFile.getAppPath() + fileSuffix, true);
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
					objectOutputStream.writeObject(obj);
				} catch (IOException e) {
					System.out.println("写入失败, IO异常");
				}
			}
		}
	}

	public static void main(String[] main)
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, CustomException {

		Student student = new Student(1234, "男", "jyz", "软件2018-01班", "123456", "软件工程", new ArrayList<>(),
				new ArrayList<>(), new ArrayList<>());
		AppFile.writeSerializableEntity(student, "student.txt");
		Student result = (Student) AppFile.readSerializableEntity("student.txt", null, student.getClass());
		System.out.println(result.getStudentName());
	}
}
