package ex.ch17.ex1706;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.DriverManager;

/**
 * @author Jiang Yinzuo
 */
public class TransactionDemo {
    public static void main(String[] args) {
        ResultSet rs;
        String insertSql1 = "INSERT INTO book(bookId,bookName,author,publisher,price) VALUES(1, '���ݽṹ', '���', '�廪��ѧ������', 39.5)";
        String insertSql2 = "INSERT INTO book(bookId,bookName,author,publisher,price) VALUES(2, '���ݿ������ʵ��', '����', '�廪��ѧ������', 48)";
        String insertSql3 = "INSERT INTO book(bookId,bookName,author,publisher,price) VALUES(3, '���ģʽ֮��', '��С��', '��е��ҵ������', 89)";
        String selectSql = "SELECT * FROM book";
        boolean ynRollback = true;
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8", "root", "ABCabc123!@#");
                Statement stmt = conn.createStatement();
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            stmt.executeUpdate(insertSql1);
            Savepoint s1 = conn.setSavepoint();
            stmt.executeUpdate(insertSql2);
            stmt.executeUpdate(insertSql3);
            conn.rollback(s1);
            conn.commit();
            conn.setAutoCommit(autoCommit);
            rs = stmt.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println(rs.getString("bookName") + " " + rs.getString("author") + " " + rs.getDouble("price"));
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
