package pers.jiangyinzuo.rollcall.domain.repository;

import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class StudentRepo {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }
    public Student getStudentByRollCallId(Long rollCallId) {
        if (student != null && rollCallId.equals(student.getStudentId())) {
            return student;
        } else {

            return null;
        }
    }

    public List<Student> getTeachingClassList(Long classId) {
        TeachingClassDao teachingClassDao = DaoFactory.createDao(TeachingClassDao.class);
        // TODO
        return null;
    }
}
