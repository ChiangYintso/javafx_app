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
    private TeachingClassDao teachingClassDao = DaoFactory.createDao(TeachingClassDao.class);

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

    public List<Student> getStudentList(Long classId) {
        return teachingClassDao.queryStudentList(classId);
    }
}
