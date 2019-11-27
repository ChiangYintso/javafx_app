package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.entity.Student;
import pers.jiangyinzuo.rollcall.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassDaoFileImpl implements TeachingClassDao {

    private static final String FILE_NAME = "teachingClasses.txt";

    @Override
    public void insertTeachingClass(TeachingClass teachingClass) throws IOException,
            IllegalArgumentException, SecurityException {
        FileHelper.writeSerializableEntity(teachingClass, FILE_NAME);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId)
            throws ClassNotFoundException, IOException {
        return FileHelper.<TeachingClass>filterEntities(studentId, TeachingClass::isSelectedThisClass, FILE_NAME);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId)
            throws ClassNotFoundException, IOException {
        return FileHelper.<TeachingClass>filterEntities(teacherId, TeachingClass::isTeachThisClass, FILE_NAME);
    }

    public static void main(String[] args)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException, IOException, ClassNotFoundException, CustomException {
        TeachingClassDao teachingClassDaoImpl = new TeachingClassDaoFileImpl();

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(123L, (byte) 1, "jyz", "123456", "�������"));

        teachingClassDaoImpl.insertTeachingClass(
                new TeachingClass(2L, "�ߵ���ѧ", 201901, 123457, (short) 2, "���޼��", "3-17��", 32, 123L, studentList));

        teachingClassDaoImpl.insertTeachingClass(
                new TeachingClass(3L, "���Դ���", 201901, 121257, (short) 2, "���޼��", "3-17��", 34, 123L, studentList));

        List<TeachingClass> teachingClassList = teachingClassDaoImpl
                .queryTeachingClassesByTeacherId(123L);
        System.out.println(teachingClassList.size());
        for (TeachingClass cls : teachingClassList) {
            System.out.println(cls.getClassName() + cls.getSession());
        }
    }
}
