package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author Jiang Yinzuo
 */
public class StudentDaoMysqlImpl implements StudentDao {
    /**
     * 插入学生信息
     *
     * @param student
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Override
    public void insertStudent(Student student) throws IllegalArgumentException, SecurityException, SQLException {
        String sql = "INSERT INTO rollcall_student(`student_id`, `student_name`, `gender`, `password`, `major`) VALUES(?, ?, ?, ?, ?)";
        int result = MySqlHelper.executeUpdate(sql, student.getStudentId(), student.getStudentName(), student.getGender(), student.getPwd(), student.getMajor());
        System.out.println(result);
    }

    /**
     * 根据学号查找学生信息
     *
     * @param studentId
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Override
    public Student queryStudent(Long studentId) throws CustomException, FileNotFoundException, IOException {
        return null;
    }

    /**
     * 根据学号和密码查找学生, 用于登录
     *
     * @param studentId 学号
     * @param password  密码
     * @return 若存在，返回学生实体类；若不存在，返回null
     */
    @Override
    public Student queryStudent(Long studentId, String password) throws IOException, ClassNotFoundException {
        // TODO
        return null;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        StudentDao studentDao = new StudentDaoMysqlImpl();
        try {
            studentDao.insertStudent(new Student(2018112664L, (byte) 1, "123456", "江胤佐", "软件工程"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
