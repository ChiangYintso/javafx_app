package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl;

import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class ScheduleUI implements UI {

	@Override
	public MENU showUI() {
		System.out.println("-------------");
		System.out.println("�α�");
		System.out.println("-------------");
		System.out.println("0. ����");
		
		return MENU.EXIT;
	}

}
