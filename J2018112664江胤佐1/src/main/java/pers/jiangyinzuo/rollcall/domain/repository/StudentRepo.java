package pers.jiangyinzuo.rollcall.domain.repository;

import pers.jiangyinzuo.rollcall.domain.entity.Student;

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
}
