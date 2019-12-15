package pers.jiangyinzuo.rollcall.dao.mysqlimpl;

import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
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
        String sql = "SELECT rollcall_teaching_class.* FROM" +
                " rollcall.rollcall_teaching_class, rollcall.rollcall_class_selection " +
                " WHERE rollcall.rollcall_class_selection.student_id = ? AND rollcall.rollcall_class_selection.student_id = rollcall_class_selection.student_id ";
        return MySqlHelper.queryMany(TeachingClass.class, sql, studentId);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) {
        String sql = "SELECT * FROM " +
                "rollcall_teaching_class WHERE teacher_id = ?";
        return MySqlHelper.queryMany(TeachingClass.class, sql, teacherId);
    }

    @Override
    public List<Student> queryStudentList(Long classId) {
        String sql = "SELECT rollcall_student.* FROM rollcall.rollcall_student, rollcall.rollcall_class_selection " +
                "WHERE rollcall_class_selection.class_id = ? AND rollcall_class_selection.student_id = rollcall_student.student_id";
        return MySqlHelper.queryMany(Student.class, sql, classId);
    }

    @Override
    public void deleteClassSelectionRecord(Long classId, Long studentId) {
        String sql = "DELETE FROM rollcall.rollcall_class_selection WHERE class_id = ? AND student_id = ?";
        MySqlHelper.executeUpdate(sql, classId, studentId);
    }

    @Override
    public void deleteClassSelectionRecords(Long classId) {
        String sql = "DELETE FROM rollcall.rollcall_class_selection WHERE class_id = ?";
        MySqlHelper.executeUpdate(sql, classId);
    }

    @Override
    public void insertClassSelectionRecord(Long classId, Long studentId) {
        String sql = "INSERT INTO rollcall.rollcall_class_selection(student_id, class_id) VALUES (?, ?)";
        MySqlHelper.executeUpdate(sql, studentId, classId);
    }

    @Override
    public void deleteClass(Long classId) {
        String sql = "DELETE FROM rollcall.rollcall_teaching_class WHERE class_id = ?";
        MySqlHelper.executeUpdate(sql, classId);
    }

    @Override
    public void updateTeachingClass(TeachingClass selectedTeachingClass) {
        String sql = "UPDATE rollcall.rollcall_teaching_class SET " +
                "class_name = ?, class_intro = ?, class_session = ?" +
                ", classroom = ?, credit = ?, weeks = ? WHERE  class_id = ?";
        MySqlHelper.executeUpdate(sql, selectedTeachingClass.getClassName(),
                selectedTeachingClass.getIntro(), selectedTeachingClass.getSession(),
                selectedTeachingClass.getClassroom(), selectedTeachingClass.getCredit(),
                selectedTeachingClass.getWeeks(), selectedTeachingClass.getClassId());
    }
}
