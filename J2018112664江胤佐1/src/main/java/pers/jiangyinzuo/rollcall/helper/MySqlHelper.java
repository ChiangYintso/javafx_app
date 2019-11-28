package pers.jiangyinzuo.rollcall.helper;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
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
    private static final String URL = "jdbc:mysql://localhost:3306/rollcall?serverTimezone=GMT%2B8";

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static String driver;
    private static Connection conn;

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

    public static ResultSet executeQuery(String sql, Object... parameters) {
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
        int result =  preparedStatement.executeUpdate();
        closeConnection();
        return result;
    }

    /**
     * ����ִ��SQL�������
     * @param sql SQL���
     * @param parametersList �����б�
     * @return ÿ������Ӱ����������
     * @throws SQLException SQLִ���쳣
     */
    public static int[] bulkExecuteUpdate(String sql, List<List<Object>> parametersList) throws SQLException {
        setPreparedStatement(sql);
        for (List<Object> list : parametersList) {
            addBatch(list);
        }
        int[] results = preparedStatement.executeBatch();
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
     * @param clazz ʵ�����class
     * @param resultSet ��ȡ���ݿ�õ��Ľ����
     * @param <T> ʵ����
     * @return ʵ����
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SQLException
     */
    private static <T> T mapRecordToEntity(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {

        // �������ݴ������
        T dto = clazz.getDeclaredConstructor().newInstance();

        // ��ȡʵ�����ʵ����
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
        }

        while (resultSet.next()) {
            for (Field field: fields) {
                // ��ȡʵ����ÿ��ʵ�����ϵ�`FieldMapper`ע��
                FieldMapper fieldMapper = field.getAnnotation(FieldMapper.class);

                if (fieldMapper != null) {
                    String columnName = fieldMapper.name();
                    Object value = resultSet.getObject(columnName);
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
                            sql.append(columnName);
                            sql.append(" = ?");
                        }
                        ResultSet tempResultSet = executeQuery(sql.toString(), value);
                        field.set(dto, mapRecordToEntity(field.getType(), tempResultSet));
                    }
                }
            }
        }
        return dto;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException {
        String sql = "SELECT * FROM rollcall_rollcall_record WHERE rollcall_id = 3";
        RollCall rollCall = mapRecordToEntity(RollCall.class, executeQuery(sql));
        System.out.println(rollCall.getRollCallInfo());
        System.out.println(rollCall.getStudent().getStudentId());
        System.out.println(rollCall.getTeachingClass());
    }
}