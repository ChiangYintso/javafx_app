package main.java.pers.jiangyinzuo.rollcall.helper;

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

    public static ResultSet executeQuery(String sql, Object[] parameters) {
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
}