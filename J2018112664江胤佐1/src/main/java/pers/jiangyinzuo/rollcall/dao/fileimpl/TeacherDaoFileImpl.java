package main.java.pers.jiangyinzuo.rollcall.dao.fileimpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.TeacherDao;
import main.java.pers.jiangyinzuo.rollcall.entity.Teacher;
import main.java.pers.jiangyinzuo.rollcall.helper.FileHelper;

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
            List<Teacher> list = FileHelper.readAllSerializableEntities(FILE_NAME);
			for (Teacher t : list) {
				if (t.getTeacherId().equals(teacherId)) {
					return t;
				}
			}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, CustomException {
        TeacherDao s = new TeacherDaoFileImpl();
        s.insertTeacher(new Teacher(123L, "张三", "信息科学与技术学院", "男", "123456", "讲师", new ArrayList<>()));
		if (s.queryTeacher(123L).getTeacherId().equals(123L)) {
			System.out.println("OK");
		} else {
			System.out.println("FAILED");
		}
    }
}
