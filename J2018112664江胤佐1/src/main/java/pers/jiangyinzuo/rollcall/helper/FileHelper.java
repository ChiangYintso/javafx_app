package pers.jiangyinzuo.rollcall.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


/**
 * �ļ���д��, ��������IO����
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
			System.out.println("д��ɹ�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����equals������ȡ��t��ȵ�ʵ����
	 * @param fileName
	 * @param t �Ƚ϶���
	 * @param <T>
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T readSerializableEntity(String fileName, T t) {
		try (FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName));
			 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			T result;
			while ((result = (T) objectInputStream.readObject()) != null) {
				if (t.equals(result)) {
					return result;
				}
			}
			return null;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
					System.out.println("д��ʧ��, IO�쳣");
				}
			}
		}
	}

	/**
	 * ����id��ָ������ɸѡʵ��
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
	 * ����id��ָ������ɸѡʵ��
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
