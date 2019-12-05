package ex.ch17.ex1703;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jiang Yinzuo
 */
public class StatementDemo {
    public static void main(String[] args) {
        ResultSet rs;
        String selectSql = "SELECT * FROM book";
        String insertSql = "INSERT INTO book VALUES(4,'java������ƻ���','�¹���','�廪��ѧ������',49.0)";
        String updateSql = "UPDATE book SET price=50.0 WHERE bookId = 4";
        String deleteSql = "DELETE FROM book WHERE bookId = 4";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hellojyz?serverTimezone=GMT%2B8", "root", "ABCabc123!@#");
                Statement stmt = conn.createStatement()
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            int count = stmt.executeUpdate(insertSql);
            System.out.println("���" + count + "����¼");
            rs = stmt.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println(rs.getString("bookName") + " " + rs.getString(3) + " " + rs.getDouble(5));
            }
            count = stmt.executeUpdate(updateSql);
            System.out.println("�޸�" + count + "����¼");
            rs = stmt.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println(rs.getString("bookName") + " " + rs.getString("author") + " " + rs.getDouble("price"));
            }
            count = stmt.executeUpdate(deleteSql);
            System.out.println("ɾ��" + count + "����¼");
            rs = stmt.executeQuery(selectSql);
            if (rs.next()) {
                System.out.println(rs.getString("bookName") + " " + rs.getString("author") + " " + rs.getDouble("price"));
            } else {
                System.out.println("û�в�ѯ������");
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

