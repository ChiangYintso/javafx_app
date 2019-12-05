package ex.ch17.ex1704;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jiang Yinzuo
 */
public class PreparedStatement {
    public static void main(String[] args) {
        java.sql.PreparedStatement ps;
        ResultSet rs;
        String selectSql = "SELECT * FROM book";
        String insertSql = "INSERT INTO book VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8", "root", "ABCabc123!@#")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ps = conn.prepareStatement(insertSql);
            ps.setInt(1, 4);
            ps.setString(2, "Java程序设计基础");
            ps.setString(3, "陈国军");
            ps.setString(4, "清华大学出版社");
            ps.setDouble(5, 49.0);
            int count = ps.executeUpdate();
            System.out.println("添加" + count + "条记录");
            rs = ps.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println(rs.getString("bookName") + " " + rs.getString(3) + " " + rs.getDouble(5));
            }
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
