```Java
	/**
	 * 序列化读取实体类
	 * @param fileName 文件名
	 * @param <T> 需要读取的实体类
	 * @return 实体类列表
	 */
	public static <T> List<T> readAllSerializableEntities(String fileName) {
		List<T> results = new ArrayList<>();

		try (FileInputStream fileInputStream = new FileInputStream(FileHelper.getFile(fileName))){
			while (fileInputStream.available() > 0) {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				// 读取实体类并加入列表
				results.add((T) objectInputStream.readObject());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
```

