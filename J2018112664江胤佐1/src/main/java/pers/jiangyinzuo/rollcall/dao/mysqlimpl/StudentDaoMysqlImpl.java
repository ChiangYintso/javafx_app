package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
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
     * ����ѧ����Ϣ
     *
     * @param student �������ѧ��ʵ����
     * @throws SecurityException
     * @throws IllegalArgumentException
     */
    @Override
    public void insertStudent(Student student) {
        String sql = "INSERT INTO rollcall_student(`student_id`, `student_name`, `gender`, `password`, `major`) VALUES(?, ?, ?, ?, ?)";
        MySqlHelper.executeUpdate(sql, student.getStudentId(), student.getStudentName(), student.getGender(), student.getPwd(), student.getMajor());
    }

    /**
     * ����ѧ�Ų���ѧ����Ϣ
     *
     * @param studentId ѧ��
     * @return ���ҵõ���ѧ��ʵ����
     */
    @Override
    public Student queryStudent(Long studentId) {
        String sql = "SELECT * FROM rollcall_student WHERE student_id = ?";
        return MySqlHelper.queryOne(Student.class, sql, studentId);
    }

    /**
     * ����ѧ�ź��������ѧ��, ���ڵ�¼
     *
     * @param studentId ѧ��
     * @param password  ����
     * @return �����ڣ�����ѧ��ʵ���ࣻ�������ڣ�����null
     */
    @Override
    public Student queryStudent(Long studentId, String password) {
        String sql = "SELECT * FROM rollcall_student WHERE student_id = ? AND password = ?";
        return MySqlHelper.queryOne(Student.class, sql, studentId, password);
    }

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoMysqlImpl();
        Student student = studentDao.queryStudent(2018112664L, "123456");
        if (student == null) {
            System.out.println("no such student");
        } else {
            System.out.println(student.getStudentId());
            System.out.println(student.getPwd());
        }
    }
}
