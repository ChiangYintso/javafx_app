package pers.jiangyinzuo.rollcall.service.impl;

import pers.jiangyinzuo.rollcall.dao.StudentDao;
import pers.jiangyinzuo.rollcall.dao.mysqlimpl.StudentDaoMysqlImpl;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.factory.DaoFactory;
import pers.jiangyinzuo.rollcall.service.StudentService;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = DaoFactory.createDao(StudentDao.class);
    @Override
    public Student queryStudent(Long studentId) {
        return studentDao.queryStudent(studentId);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentDao.deleteStudent(studentId);
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

}
