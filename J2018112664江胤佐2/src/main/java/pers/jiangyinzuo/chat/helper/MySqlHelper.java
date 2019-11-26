package pers.jiangyinzuo.chat.helper;

import java.sql.*;

/**
 * 连接Mysql的辅助类
 * @author Jiang Yinzuo
 */
public class MySqlHelper {
	private static final String username = "root";
	private static final String password = "ABCabc123!@#";
	private static final String url = "jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8";

	private static PreparedStatement preparedStatement;
	private static Statement statement;
	private static String driver;
	private static Connection conn;

	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getConnection();
	}

	/**
	 * 单例模式获取连接
	 * @return
	 */
	private static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(url, username, password);
			}
		} catch (SQLException e) {
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

	public static int executeUpdate(String sql, Object[] parameters) {
		loadPreparedStatement(sql, parameters);
		int rowCount = -1;
		try {
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	private static void loadPreparedStatement(String sql, Object[] parameters) {
		try {
			preparedStatement = conn.prepareStatement(sql);
			for (int i = 1; i <= parameters.length; ++i) {
				preparedStatement.setObject(i, parameters[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
