package pers.jiangyinzuo.rollcall.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import pers.jiangyinzuo.rollcall.dao.fileimpl.TeachingClassDaoFileImpl;
import pers.jiangyinzuo.rollcall.service.validator.Validator;
import pers.jiangyinzuo.rollcall.common.CustomException;

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

	public static void writeSerializableEntity(Object obj, String fileName) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(FileHelper.getFile(fileName), true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(obj);
			System.out.println("写入成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object readSerializableEntity(String fileSuffix, Validator query, Class clazz, Object... obj)
			throws IOException, ClassNotFoundException, CustomException {
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

	/**
	 * 调用equals方法读取和t相等的实体类
	 * @param fileName
	 * @param t 比较对象
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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

	public static <T> List<T> readAllSerializableEntities(String fileName) {
		List<T> results = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName));){
			while (fileInputStream.available() > 0) {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				results.add((T) objectInputStream.readObject());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return results;
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

	/**
	 * 根据id和指定方法筛选实体
	 * @param id
	 * @param filter
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> filterEntities(Long id, BiFunction<T, Long, Boolean> filter, String fileName) throws IOException, ClassNotFoundException {
		List<T> list = FileHelper.<T>readAllSerializableEntities(fileName);
		List<T> results = new ArrayList<>();
		for (T t : list) {
			if (filter.apply(t, id)) {
				results.add(t);
			}
		}
		return results;
	}

	/**
	 * 根据id和指定方法筛选实体
	 * @param id
	 * @param filter
	 * @param fileName
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T filterEntity(Long id, BiFunction<T, Long, Boolean> filter, String fileName) throws IOException, ClassNotFoundException {
		List<T> list = FileHelper.<T>readAllSerializableEntities(fileName);
		for (T t : list) {
			if (filter.apply(t, id)) {
				return t;
			}
		}
		return null;
	}
}
