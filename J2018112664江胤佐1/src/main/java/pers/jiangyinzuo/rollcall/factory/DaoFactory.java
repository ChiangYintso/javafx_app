package pers.jiangyinzuo.rollcall.factory;

import pers.jiangyinzuo.rollcall.dao.RollCallDao;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * @author Jiang Yinzuo
 */
public class DaoFactory {
    private static final String DAO_PACKAGE_NAME = "pers.jiangyinzuo.rollcall.dao";

    private static Properties props = new Properties();
    private static String daoImpl;

    // 使用静态代码块为Properties对象赋值
    static {
        // 获取properties流对象
        try (InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties")) {
            assert in != null;
            props.load(in);
            daoImpl = props.getProperty("dao");
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 工厂模式 + 反射 创建Dao
     * @param daoInterface 需要创建的Dao接口
     * @param <T>
     * @return 对应Dao的实现类
     */
    public static <T> T createDao(Class<T> daoInterface) {
        try {
            return (T) Class.forName(DAO_PACKAGE_NAME + "." + daoImpl.toLowerCase() + "impl." + daoInterface.getSimpleName() + daoImpl + "Impl").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RollCallDao rollCallDao = createDao(RollCallDao.class);
        System.out.println(rollCallDao.toString());
        System.out.println(RollCallDao.class.getName());
    }
}