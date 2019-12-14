package pers.jiangyinzuo.rollcall.service;

import pers.jiangyinzuo.rollcall.domain.entity.Student;

/**
 * @author Jiang Yinzuo
 */
public interface StudentService {
    Student queryStudent(Long studentId);

    void updateStudent(Student student);

    void deleteStudent(Long studentId);

    void insertStudent(Student student);
}

