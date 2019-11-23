package main.java.pers.jiangyinzuo.chat.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MySQLHelper {
	private static final String username = "root";
	private static final String password = "ABCabc123!@#";
	private static final String url = "jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8";
	private static PreparedStatement preparedStatement;
	private static Statement statement;
	private static String driver;
	private static Connection conn;

	/**
	 * 单例模式获取连接
	 * @return
	 */
//	private static Connection getConnection() {
//		try {
//			if (conn == null) {
//				
//			}
//		}
//	}
}
