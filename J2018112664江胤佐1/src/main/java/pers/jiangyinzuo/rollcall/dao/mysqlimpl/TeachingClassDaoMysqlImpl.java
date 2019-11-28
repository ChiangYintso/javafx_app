package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassDaoMysqlImpl implements TeachingClassDao {
    @Override
    public void insertTeachingClass(TeachingClass teachingClass) throws IllegalArgumentException {
        String sql = "INSERT INTO rollcall_teaching_class(class_name, teacher_id, class_session, " +
                "course_code, classroom, class_intro, semester, credit, weeks)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            MySqlHelper.executeUpdate(sql, teachingClass.getClassName(),
                    teachingClass.getTeacherId(), teachingClass.getSession(),
                    teachingClass.getCourseCode(), teachingClass.getClassroom(),
                    teachingClass.getIntro(), teachingClass.getSemester(),
                    teachingClass.getCredit(), teachingClass.getWeeks());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId) {
        String sql = "SELECT * FROM" +
                " rollcall_teaching_class" +
                "INNER JOIN rollcall_class_selection " +
                "ON rollcall_teaching_class.class_id = ?" +
                "AND rollcall_class_selection.class_id = ?";
        try {
            return MySqlHelper.queryMany(TeachingClass.class, sql, studentId, studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) throws FileNotFoundException, ClassNotFoundException, IOException, CustomException {
        String sql = "SELECT * FROM " +
                "rollcall_teaching_class WHERE teacher_id = ?";
        try {
            return MySqlHelper.queryMany(TeachingClass.class, sql, teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
