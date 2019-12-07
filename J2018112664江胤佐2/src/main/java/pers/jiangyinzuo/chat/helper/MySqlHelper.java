package pers.jiangyinzuo.chat.helper;

import pers.jiangyinzuo.chat.domain.mapper.FieldMapper;
import pers.jiangyinzuo.chat.domain.mapper.TableMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ����Mysql�ĸ�����
 *
 * @author Jiang Yinzuo
 */
public class MySqlHelper {
    /**
     * �������ݿ���˺š����롢URL
     */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ABCabc123!@#";
    private static final String URL = "jdbc:mysql://localhost:3306/chat?serverTimezone=GMT%2B8";

    private static PreparedStatement preparedStatement;
    private static Connection conn;
    private static ResultSet resultSet;

    static {
        getConnection();
    }

    /**
     * ����ģʽ��ȡ����
     *
     * @return Connection��������
     */
    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static <T> List<T> queryMany(Class<T> clazz, String sql, Object... parameters) throws SQLException {
        getConnection();
        try (ResultSet resultSet = executeQuery(sql, parameters)) {
            if (Number.class.isAssignableFrom(clazz)) {
                return mapRecordsToNumber(clazz, resultSet);
            }
            return mapRecordsToEntities(clazz, resultSet);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        closeConnection();
        return null;
    }

    public static <T> T queryOne(Class<T> clazz, String sql, Object... parameters) {
        getConnection();
        try (ResultSet resultSet = executeQuery(sql, parameters)) {
            return mapRecordToEntity(clazz, resultSet);
        } catch (SQLException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet executeQuery(String sql, Object... parameters) {
        getConnection();
        loadPreparedStatement(sql, parameters);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int executeUpdate(String sql, Object... parameters) throws SQLException {
        getConnection();
        loadPreparedStatement(sql, parameters);
        int result = preparedStatement.executeUpdate();
        closeConnection();
        return result;
    }

    public static Long executeUpdateReturnPrimaryKey(String sql, Object... parameters) throws SQLException {
        getConnection();
        preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i <= parameters.length; ++i) {
            preparedStatement.setObject(i, parameters[i - 1]);
        }
        Long key = -1L;
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            key = resultSet.getLong(1);
        }
        closeConnection();
        return key;
    }

    /**
     * ����ִ��SQL�������
     *
     * @param sql            SQL���
     * @param parametersList �����б�
     * @return ÿ������Ӱ����������
     * @throws SQLException SQLִ���쳣
     */
    public static int[] bulkExecuteUpdate(String sql, List<List<Object>> parametersList) throws SQLException {
        getConnection();
        setPreparedStatement(sql);

        for (List<Object> list : parametersList) {
            addBatch(list);
        }
        int[] results = preparedStatement.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
        closeConnection();
        return results;
    }

    private static void setPreparedStatement(String sql) throws SQLException {
        conn.setAutoCommit(false);
        preparedStatement = conn.prepareStatement(sql);
    }

    private static void addBatch(List<Object> parameters) throws SQLException {
        for (int i = 1; i <= parameters.size(); ++i) {
            preparedStatement.setObject(i, parameters.get(i - 1));
        }
        preparedStatement.addBatch();
    }

    private static void loadPreparedStatement(String sql, Object[] parameters) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 1; i <= parameters.length; ++i) {
                preparedStatement.setObject(i, parameters[i - 1]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ݿ��еļ�¼ӳ��Ϊʵ����
     *
     * @param clazz     ʵ�����class
     * @param resultSet ��ȡ���ݿ�õ��Ľ����
     * @param <T>       ʵ����
     * @return ����¼���ڣ�����ʵ���ࣻ���򷵻�null
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SQLException
     */
    private static <T> T mapRecordToEntity(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        if (resultSet == null || !resultSet.next()) {
            return null;
        }
        // �������ݴ������
        T dto = clazz.getDeclaredConstructor().newInstance();

        // ��ȡʵ�����ʵ����
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        do {
            mapFields(resultSet, dto, fields);
        } while (resultSet.next());
        return dto;
    }

    private static <T> List<T> mapRecordsToEntities(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        List<T> results = new ArrayList<>();
        if (resultSet == null || !resultSet.next()) {
            return results;
        }

        // ��ȡʵ�����ʵ����
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        do {
            // �������ݴ������
            T dto = clazz.getDeclaredConstructor().newInstance();
            mapFields(resultSet, dto, fields);
            results.add(dto);
        } while (resultSet.next());
        return results;
    }

    private static <T> List<T> mapRecordsToNumber(Class<T> clazz, ResultSet resultSet) {
        List<T> results = new ArrayList<>();
        try {
            if (resultSet == null || !resultSet.next()) {
                return results;
            }
            do {
                T number = (T)resultSet.getObject(1);
                results.add(number);
            } while(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * �����ݿ��¼ӳ�䵽dto��
     * @param resultSet MySQL��ѯ�õ��Ľ����
     * @param dto ���ݴ������
     * @param fields ʵ��������
     * @param <T> ��������
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static <T> void mapFields(ResultSet resultSet, T dto, Field[] fields) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        for (Field field : fields) {
            // ��ȡʵ����ÿ��ʵ�����ϵ�`FieldMapper`ע��
            FieldMapper fieldMapper = field.getAnnotation(FieldMapper.class);

            if (fieldMapper != null) {
                // ���ݿ����ֶ���
                String columnName = fieldMapper.name();
                // �ֶζ�Ӧ��ֵ
                Object value;
                try {
                    value = resultSet.getObject(columnName);
                    if ("column".equals(fieldMapper.type())) {
                        try {
                            field.set(dto, value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("reference".equals(fieldMapper.type())) {
                        StringBuilder sql = new StringBuilder("SELECT * FROM ");
                        TableMapper tableMapper = field.getType().getAnnotation(TableMapper.class);
                        if (tableMapper != null) {
                            // ����
                            sql.append(tableMapper.value());
                            sql.append(" WHERE ");
                            // �ֶ���
                            sql.append("".equals(fieldMapper.joinName()) ? columnName : fieldMapper.joinName());
                            sql.append(" = ?");
                        }
                        ResultSet tempResultSet = executeQuery(sql.toString(), value);
                        field.set(dto, mapRecordToEntity(field.getType(), tempResultSet));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}