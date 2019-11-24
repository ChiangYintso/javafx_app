package main.java.pers.jiangyinzuo.rollcall.helper;

import java.sql.*;

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

    {
        getConnection();
    }

    /**
     * ����ģʽ��ȡ����
     *
     * @return
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
        if (conn == null) {
            getConnection();
        }
        loadPreparedStatement(sql, parameters);
        return preparedStatement.executeUpdate();
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