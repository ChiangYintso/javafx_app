package pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import pers.jiangyinzuo.rollcall.common.CustomException;
import pers.jiangyinzuo.rollcall.dao.TeacherDao;
import pers.jiangyinzuo.rollcall.domain.entity.Teacher;
import pers.jiangyinzuo.rollcall.helper.FileHelper;

/**
 * @author Jiang Yinzuo
 */
public class TeacherDaoFileImpl implements TeacherDao {

    private static final String FILE_NAME = "teachers.txt";

    @Override
    public void insertTeacher(Teacher teacher) throws IOException, IllegalArgumentException,
            SecurityException {
        FileHelper.writeSerializableEntity(teacher, FILE_NAME);
    }

    @Override
    public Teacher queryTeacher(Long teacherId) {
        try {
            return FileHelper.<Teacher>filterEntity(teacherId, Teacher::equals, FILE_NAME);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����˺����������ʦ, ���ڵ�¼��֤
     *
     * @param teacherId ��ʦ�˺�
     * @param password  ����
     * @return �����ҳɹ����ؽ�ʦʵ����, ���򷵻�null
     */
    @Override
    public Teacher queryTeacher(Long teacherId, String password) throws IOException, ClassNotFoundException {
        Teacher teacher = FileHelper.readSerializableEntity(FILE_NAME, new Teacher.Builder()
                .teacherId(teacherId)
                .password(password)
                .build());
        if (teacher == null || password != null && !password.equals(teacher.getPassword())) {
            return null;
        } else {
            return teacher;
        }
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, CustomException {
        TeacherDao s = new TeacherDaoFileImpl();
        s.insertTeacher(new Teacher(123L, "����", "��Ϣ��ѧ�뼼��ѧԺ", true, "123456", "��ʦ"));
        if (s.queryTeacher(123L).getTeacherId().equals(123L)) {
            System.out.println("OK");
        } else {
            System.out.println("FAILED");
        }
    }
}
