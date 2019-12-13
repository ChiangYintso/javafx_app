package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.MySqlHelper;

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
        MySqlHelper.executeUpdate(sql, teachingClass.getClassName(),
                teachingClass.getTeacherId(), teachingClass.getSession(),
                teachingClass.getCourseCode(), teachingClass.getClassroom(),
                teachingClass.getIntro(), teachingClass.getSemester(),
                teachingClass.getCredit(), teachingClass.getWeeks());
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId) {
        String sql = "SELECT * FROM" +
                " rollcall_teaching_class" +
                "INNER JOIN rollcall_class_selection " +
                "ON rollcall_teaching_class.class_id = ?" +
                "AND rollcall_class_selection.class_id = ?";
        return MySqlHelper.queryMany(TeachingClass.class, sql, studentId, studentId);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) {
        String sql = "SELECT * FROM " +
                "rollcall_teaching_class WHERE teacher_id = ?";
        return MySqlHelper.queryMany(TeachingClass.class, sql, teacherId);
    }
}
