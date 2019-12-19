package pers.jiangyinzuo.rollcall.helper;

import pers.jiangyinzuo.rollcall.domain.mapper.FieldMapper;
import pers.jiangyinzuo.rollcall.domain.mapper.TableMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
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

    private static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> List<T> queryMany(Class<T> clazz, String sql, Object... parameters) {
        getConnection();
        try (ResultSet resultSet = executeQuery(sql, parameters)) {
            if (Number.class.isAssignableFrom(clazz) || String.class.isAssignableFrom(clazz)) {
                return mapRecordsSystemType(clazz, resultSet);
            }
            return mapRecordsToEntities(clazz, resultSet);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return null;
    }

    /**
     * 查询单条记录
     * @param clazz 需要返回的泛型类
     * @param sql 需要执行的SQL语句
     * @param parameters 需要在SQL语句中传入的可变参数
     * @param <T> 需要返回的泛型
     * @return 查询得到的结果
     */
    public static <T> T queryOne(Class<T> clazz, String sql, Object... parameters) {
        // 获取数据库连接
        getConnection();

        // 对JDBC的简单封装，返回查询得到的ResultSet
        try (ResultSet resultSet = executeQuery(sql, parameters)) {
            // 调用映射方法，将结果集映射成实体类
            return mapRecordToEntity(clazz, resultSet);
        } catch (SQLException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
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

    public static int executeQueryCount(String sql, Object... parameters) {
        getConnection();
        loadPreparedStatement(sql, parameters);
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int executeUpdate(String sql, Object... parameters) {
        getConnection();
        loadPreparedStatement(sql, parameters);
        int result = 0;
        try {
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
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
     * 批量执行SQL更新语句
     *
     * @param sql            SQL语句
     * @param parametersList 参数列表
     * @return 每条语句的影响行数数组
     * @throws SQLException SQL执行异常
     */
    public static int[] bulkExecuteUpdate(String sql, List<List<Object>> parametersList) {
        getConnection();
        try {
            setPreparedStatement(sql);
            for (List<Object> list : parametersList) {
                addBatch(list);
            }
            int[] results = preparedStatement.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            closeConnection();

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
     *
     * @param clazz     实体类的class
     * @param resultSet 读取数据库得到的结果集
     * @param <T>       实体类
     * @return 若记录存在，返回实体类；否则返回null
     */
    private static <T> T mapRecordToEntity(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        if (resultSet == null || !resultSet.next()) {
            return null;
        }
        // 构造数据传输对象
        T dto = clazz.getDeclaredConstructor().newInstance();

        // 获取实体类的实例域
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        do {
            // 递归调用mapFields()方法
            mapFields(resultSet, dto, fields);
        } while (resultSet.next());
        return dto;
    }

    private static <T> List<T> mapRecordsToEntities(Class<T> clazz, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        List<T> results = new ArrayList<>();
        if (resultSet == null || !resultSet.next()) {
            return results;
        }

        // 获取实体类的实例域
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        do {
            // 构造数据传输对象
            T dto = clazz.getDeclaredConstructor().newInstance();
            mapFields(resultSet, dto, fields);
            results.add(dto);
        } while (resultSet.next());
        return results;
    }

    private static <T> List<T> mapRecordsSystemType(Class<T> clazz, ResultSet resultSet) {
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
     * 将数据库记录映射到dto上
     * @param resultSet MySQL查询得到的结果集
     * @param dto 数据传输对象
     * @param fields 实例域数组
     * @param <T> 对象类型
     */
    private static <T> void mapFields(ResultSet resultSet, T dto, Field[] fields) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        for (Field field : fields) {
            // 获取实体类每个实例域上的`FieldMapper`注解
            FieldMapper fieldMapper = field.getAnnotation(FieldMapper.class);

            if (fieldMapper != null) {
                // 数据库表的字段名
                String columnName = fieldMapper.name();
                // 字段对应的值
                Object value;
                try {
                    value = resultSet.getObject(columnName);
                    if ("column".equals(fieldMapper.type())) {
                        // 映射的实例成员是某个字段的值
                        try {
                            field.set(dto, value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if ("reference".equals(fieldMapper.type())) {
                        // 若需要映射的实例成员是一个实体，则调用 mapRecordToEntity()方法进行递归的映射
                        StringBuilder sql = new StringBuilder("SELECT * FROM ");
                        TableMapper tableMapper = field.getType().getAnnotation(TableMapper.class);
                        if (tableMapper != null) {
                            // 获取表名
                            sql.append(tableMapper.value());
                            sql.append(" WHERE ");
                            // 获取字段名
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