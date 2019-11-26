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

    public static <T> T createDao(Class<T> daoClass) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) Class.forName(DAO_PACKAGE_NAME + "." + daoImpl.toLowerCase() + "impl." + daoClass.getSimpleName() + daoImpl + "Impl").getDeclaredConstructor().newInstance();
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RollCallDao rollCallDao = createDao(RollCallDao.class);
        System.out.println(rollCallDao.toString());
        System.out.println(RollCallDao.class.getName());
    }
}