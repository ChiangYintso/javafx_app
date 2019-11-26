package pers.jiangyinzuo.rollcall.helper;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pers.jiangyinzuo.rollcall.service.validator.Validator;
import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.ui.console.Main;

/**
 * 文件读写类, 负责程序的IO操作
 * 
 * @author Jiang Yinzuo
 *
 */
public class FileHelper {
	private static File getFile(String fileName) {
		return new File(System.getProperty("user.dir") +"/files/"+ fileName);
	}

	public static void writeSerializableEntity(Object obj, String fileSuffix) throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(FileHelper.getFile(fileSuffix), true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
			objectOutputStream.writeObject(obj);
			System.out.println("写入成功");
		}
	}

	public static Object readSerializableEntity(String fileSuffix, Validator query, Class clazz, Object... obj)
			throws FileNotFoundException, IOException, ClassNotFoundException, CustomException {
		try (FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileSuffix));
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

	public static <T> T readSerializableEntity(String fileName, T t) throws IOException, ClassNotFoundException {
		try (FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName));
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			T result;
			while ((result = (T) objectInputStream.readObject()) != null) {
				if (t.equals(result)) {
					return result;
				}
			}
			return null;
		}
	}

	public static <T> List<T> readAllSerializableEntities(String fileName) throws ClassNotFoundException, IOException {
		List<T> results = new ArrayList<>();

		FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName));
		while (fileInputStream.available() > 0) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			results.add((T) objectInputStream.readObject());
		}
		fileInputStream.close();
		return results;
	}

	public static <T> List<T> readSerializableEntities(String fileName, Validator v, Object obj)
			throws CustomException, IOException, ClassNotFoundException {
		List<T> arrayList = new ArrayList<>();

		if (v == null) {
			return arrayList;
		}
		Object objFromFile;
		FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName));
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

	public static <T> void bulkWriteSerializableEntities(String fileName, List<T> objectList, boolean add) {
		if (objectList != null) {
			for (Object obj : objectList) {
				try (FileOutputStream fileOutputStream = new FileOutputStream(FileHelper.getFile(fileName), add);
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
					objectOutputStream.writeObject(obj);
				} catch (IOException e) {
					System.out.println("写入失败, IO异常");
				}
			}
		}
	}
}
