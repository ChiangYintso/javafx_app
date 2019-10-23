package test.java.pers.jiangyinzuo.rollcall.daoTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import main.java.pers.jiangyinzuo.rollcall.common.CustomException;
import main.java.pers.jiangyinzuo.rollcall.dao.RollCallDao;
import main.java.pers.jiangyinzuo.rollcall.dao.fileImpl.RollCallDaoFileImpl;
import main.java.pers.jiangyinzuo.rollcall.entity.RollCall;
import test.java.pers.jiangyinzuo.rollcall.context.Entity;

public class RollCallDaoFileImplTest {

	private static RollCallDao dao = new RollCallDaoFileImpl();

	private static void testRead() throws ClassNotFoundException, CustomException, IOException {
		List<RollCall> rollCallList = dao.queryRollCallsByTeachingClassId(1);

		for (RollCall rollCall : rollCallList) {
			System.out.println(rollCall.getRollCallTime() + " " + rollCall.getPresence() + " "
					+ rollCall.getTeachingClass().getClassName());
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, CustomException {

		dao.insertRollCall(Entity.rollCallList[0]);
		dao.<RollCall>bulkInsertRollCalls(Arrays.asList(Entity.rollCallList));

		testRead();
	}
}
