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

    // ʹ�þ�̬�����ΪProperties����ֵ
    static {
        // ��ȡproperties������
        try (InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties")) {
            assert in != null;
            props.load(in);
            daoImpl = props.getProperty("dao");
        } catch (Exception e) {
            throw new ExceptionInInitializerError("��ʼ��propertiesʧ��");
        }
    }

    /**
     * ����ģʽ + ���� ����Dao
     * @param daoInterface ��Ҫ������Dao�ӿ�
     * @param <T>
     * @return ��ӦDao��ʵ����
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> T createDao(Class<T> daoInterface) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) Class.forName(DAO_PACKAGE_NAME + "." + daoImpl.toLowerCase() + "impl." + daoInterface.getSimpleName() + daoImpl + "Impl").getDeclaredConstructor().newInstance();
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RollCallDao rollCallDao = createDao(RollCallDao.class);
        System.out.println(rollCallDao.toString());
        System.out.println(RollCallDao.class.getName());
    }
}