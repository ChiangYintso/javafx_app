package main.java.pers.jiangyinzuo.rollcall.dao.consoleImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import main.java.pers.jiangyinzuo.rollcall.dao.TeachingClassDao;
import main.java.pers.jiangyinzuo.rollcall.entity.TeachingClass;
import main.java.pers.jiangyinzuo.rollcall.util.AppFile;

public class TeachingClassDaoImpl implements TeachingClassDao {

	@Override
	public void insertTeachingClassDao(TeachingClass teachingClass) throws IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		AppFile.writeSerializableEntity(teachingClass, "teachingClasses.txt");
	}

	@Override
	public void queryTeachingClassesByStudentId(Integer studentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void queryTeachingClassesByTeacherId(Integer teacher) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
	}
}
