package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.TeacherDao;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author Jiang Yinzuo
 */
public class TeacherDaoMysqlImpl implements TeacherDao {
    /**
     * �����ʦ
     *
     * @param teacher ������Ľ�ʦ
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    @Override
    public void insertTeacher(Teacher teacher) throws IllegalArgumentException, SecurityException {
        String sql = "INSERT INTO rollcall_teacher(teacher_id, teacher_name, department, gender, password, title) VALUES(?, ?, ?, ?, ?, ?)";
        MySqlHelper.executeUpdate(sql, teacher.getTeacherId(), teacher.getTeacherName(), teacher.getDepartment(), teacher.getGender(), teacher.getPassword(), teacher.getTitle());
    }

    /**
     * ����id������ʦ
     *
     * @param teacherId ��ʦ����
     * @return �����ҳɹ�, ���ؽ�ʦʵ����; ���򷵻�null
     */
    @Override
    public Teacher queryTeacher(Long teacherId) {
        String sql = "SELECT * FROM rollcall_teacher WHERE teacher_id = ?";
        return MySqlHelper.queryOne(Teacher.class, sql, teacherId);
    }

    /**
     * �����˺����������ʦ, ���ڵ�¼��֤
     *
     * @param teacherId ��ʦ����
     * @param password ����
     * @return �����ҳɹ�������Ӧ�Ľ�ʦʵ���ࣻ���򷵻�null
     */
    @Override
    public Teacher queryTeacher(Long teacherId, String password) {
        String sql = "SELECT * FROM rollcall_teacher WHERE teacher_id = ? AND password = ?";
        return MySqlHelper.queryOne(Teacher.class, sql, teacherId, password);
    }

    public static void main(String[] args) {
        try {
            TeacherDao dao = DaoFactory.createDao(TeacherDao.class);
            dao.insertTeacher(new Teacher.Builder()
                    .teacherId(2018112664L)
                    .password("222")
                    .teacherName("��ط��")
                    .department("��Ϣ��ѧ�뼼��ѧԺ")
                    .gender(true)
                    .title("������")
                    .build());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
    }
}
