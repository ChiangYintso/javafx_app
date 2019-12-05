package ex.ch17.ex1705;

import java.sql.*;

/**
 * @author Jiang Yinzuo
 */
public class MethodDataDemo {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8", "root", "ABCabc123!@#");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student");
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.DatabaseMetaData dmd = conn.getMetaData();
            System.out.println("数据库产品:" + dmd.getDatabaseProductName());
            System.out.println("数据库版本" + dmd.getDatabaseMinorVersion());
            System.out.println("驱动器" + dmd.getDriverName());
            System.out.println("数据库URL" + dmd.getURL());
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("总共有：" + rsmd.getColumnCount() + "列");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println("列" + i + ":" + rsmd.getColumnName(i) + "," + rsmd.getColumnTypeName(i) + "(" + rsmd.getColumnDisplaySize(i) + ")");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
