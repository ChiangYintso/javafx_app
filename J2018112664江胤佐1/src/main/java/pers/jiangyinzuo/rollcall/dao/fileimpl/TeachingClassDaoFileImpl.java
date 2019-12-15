package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import pers.jiangyinzuo.rollcall.domain.dto.ClassSelectionRecordDTO;
import pers.jiangyinzuo.rollcall.domain.entity.Student;
import pers.jiangyinzuo.rollcall.domain.entity.TeachingClass;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class TeachingClassDaoFileImpl implements TeachingClassDao {

    private static final String FILE_NAME = "teachingClasses.txt";
    private static final String CLASS_SELECTION_FILE_NAME = "classSelection.txt";

    @Override
    public void insertTeachingClass(TeachingClass teachingClass) {
        FileHelper.writeSerializableEntity(teachingClass, FILE_NAME);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByStudentId(Long studentId) {
        return FileHelper.<TeachingClass>filterEntities(studentId, TeachingClass::isSelectedThisClass, FILE_NAME);
    }

    @Override
    public List<TeachingClass> queryTeachingClassesByTeacherId(Long teacherId) {
        return FileHelper.<TeachingClass>filterEntities(teacherId, TeachingClass::isTeachThisClass, FILE_NAME);
    }

    @Override
    public List<Student> queryStudentList(Long classId) {
        List<ClassSelectionRecordDTO> dtoList = FileHelper.readAllSerializableEntities(CLASS_SELECTION_FILE_NAME);
        List<Long> studentIdList = new ArrayList<>();
        for (ClassSelectionRecordDTO dto : dtoList) {
            if (dto.getClassId().equals(classId)) {
                studentIdList.add(dto.getStudentId());
            }
        }

        List<Student> studentList = FileHelper.readAllSerializableEntities(FILE_NAME);
        List<Student> results = new ArrayList<>();
        for (Student student : studentList) {
            for (Long studentId : studentIdList) {
                if (student.getStudentId().equals(studentId)) {
                    results.add(student);
                }
            }
        }
        return results;
    }

    @Override
    public void deleteClassSelectionRecord(Long classId, Long studentId) {
        List<ClassSelectionRecordDTO> list = FileHelper.readAllSerializableEntities(CLASS_SELECTION_FILE_NAME);
        for (ClassSelectionRecordDTO dto : list) {
            if (dto.getClassId().equals(classId) && dto.getStudentId().equals(studentId)) {
                list.remove(dto);
                break;
            }
        }
        FileHelper.bulkWriteSerializableEntities(CLASS_SELECTION_FILE_NAME, list, false);
    }

    @Override
    public void insertClassSelectionRecord(Long classId, Long studentId) {
        ClassSelectionRecordDTO dto = new ClassSelectionRecordDTO.Builder().classId(classId).studentId(studentId).build();
        FileHelper.writeSerializableEntity(dto, CLASS_SELECTION_FILE_NAME);

    }

    public static void main(String[] args)
            throws IllegalArgumentException {
        TeachingClassDao teachingClassDaoImpl = new TeachingClassDaoFileImpl();

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(123L, true, "jyz", "123456", "软件工程"));

        teachingClassDaoImpl.insertTeachingClass(
                new TeachingClass.Builder().className("高等数学").build());

        teachingClassDaoImpl.insertTeachingClass(
                new TeachingClass.Builder().className("线性代数").build());

        List<TeachingClass> teachingClassList = teachingClassDaoImpl
                .queryTeachingClassesByTeacherId(123L);
        System.out.println(teachingClassList.size());
        for (TeachingClass cls : teachingClassList) {
            System.out.println(cls.getClassName() + cls.getSession());
        }
    }
}
