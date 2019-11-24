package main.java.pers.jiangyinzuo.rollcall.helper;

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
public class FileHelper {
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
		try (FileWriter fileWriter = new FileWriter(FileHelper.getAppPath() + fileName, true);) {
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
		try (FileOutputStream fileOutputStream = new FileOutputStream(FileHelper.getAppPath() + fileSuffix, true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
			objectOutputStream.writeObject(obj);
			System.out.println("写入成功");
		}
	}

	public static Object readSerializableEntity(String fileSuffix, Validator query, Class clazz, Object... obj)
			throws FileNotFoundException, IOException, ClassNotFoundException, CustomException {
		try (FileInputStream fileInputStream = new FileInputStream(new File(FileHelper.getAppPath() + fileSuffix));
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
		}
	}

	public static <T> List<T> readAllSerializableEntities(String fileName) throws ClassNotFoundException, IOException {
		List<T> entitiesList = new ArrayList<>();
		Object objFromFile;
		FileInputStream fileInputStream = new FileInputStream(new File(FileHelper.getAppPath() + fileName));
		while (fileInputStream.available() > 0) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			objFromFile = objectInputStream.readObject();
			entitiesList.add((T) objFromFile);
		}
		fileInputStream.close();
		return entitiesList;
	}

	public static <T> List<T> readSerializableEntities(String fileSuffix, Validator v, Object obj)
			throws CustomException, IOException, ClassNotFoundException {
		List<T> arrayList = new ArrayList<>();

		if (v == null) {
			return arrayList;
		}
		Object objFromFile;
		FileInputStream fileInputStream = new FileInputStream(new File(FileHelper.getAppPath() + fileSuffix));
		while (fileInputStream.available() > 0) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			objFromFile = objectInputStream.readObject();
			if (v.validate(objFromFile, obj)) {
				arrayList.add((T) objFromFile);
			}

		}
		fileInputStream.close();
		return arrayList;
	}

	public static <T> void bulkInsertSerializableEntities(String fileSuffix, List<T> objectList, boolean add) {
		if (objectList != null) {
			for (Object obj : objectList) {
				try (FileOutputStream fileOutputStream = new FileOutputStream(FileHelper.getAppPath() + fileSuffix, add);
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
					objectOutputStream.writeObject(obj);
				} catch (IOException e) {
					System.out.println("写入失败, IO异常");
				}
			}
		}
	}
}
