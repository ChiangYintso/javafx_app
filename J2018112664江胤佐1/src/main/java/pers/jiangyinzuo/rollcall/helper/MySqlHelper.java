package pers.jiangyinzuo.rollcall.helper;

import pers.jiangyinzuo.rollcall.domain.entity.RollCall;
import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

/**
 * 连接Mysql的辅助类
 *
 * @author Jiang Yinzuo
 */
public class MySqlHelper {
    /**
     * 连接数据库的账号、密码、URL
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
     * 单例模式获取连接
     *
     * @return Connection单例对象
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
     * 批量执行SQL更新语句
     * @param sql SQL语句
     * @param parametersList 参数列表
     * @return 每条语句的影响行数数组
     * @throws SQLException SQL执行异常
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
     * 将数据库中的记录映射为实体类
     * @param clazz 实体类的class
     * @param resultSet 读取数据库得到的结果集
     * @param <T> 实体类
     * @return 实体类
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SQLException
     */
    private static <T> T mapRecordToEntity(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {

        // 构造数据传输对象
        T dto = clazz.getDeclaredConstructor().newInstance();

        // 获取实体类的实例域
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
        }

        while (resultSet.next()) {
            for (Field field: fields) {
                // 获取实体类每个实例域上的`FieldMapper`注解
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
                            // 表名
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