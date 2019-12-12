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
     * 插入教师
     *
     * @param teacher 待插入的教师
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    @Override
    public void insertTeacher(Teacher teacher) throws IllegalArgumentException, SecurityException {
        String sql = "INSERT INTO rollcall_teacher(teacher_id, teacher_name, department, gender, password, title) VALUES(?, ?, ?, ?, ?, ?)";
        MySqlHelper.executeUpdate(sql, teacher.getTeacherId(), teacher.getTeacherName(), teacher.getDepartment(), teacher.getGender(), teacher.getPassword(), teacher.getTitle());
    }

    /**
     * 根据id查找老师
     *
     * @param teacherId 教师工号
     * @return 若查找成功, 返回教师实体类; 否则返回null
     */
    @Override
    public Teacher queryTeacher(Long teacherId) {
        String sql = "SELECT * FROM rollcall_teacher WHERE teacher_id = ?";
        return MySqlHelper.queryOne(Teacher.class, sql, teacherId);
    }

    /**
     * 根据账号密码查找老师, 用于登录验证
     *
     * @param teacherId 教师工号
     * @param password 密码
     * @return 若查找成功返回相应的教师实体类；否则返回null
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
                    .teacherName("江胤佐")
                    .department("信息科学与技术学院")
                    .gender(true)
                    .title("副教授")
                    .build());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
    }
}
