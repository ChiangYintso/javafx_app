package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl;

import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class ScheduleUI implements UI {

	@Override
	public MENU showUI() {
		System.out.println("-------------");
		System.out.println("¿Î±í");
		System.out.println("-------------");
		System.out.println("0. ·µ»Ø");
		
		return MENU.EXIT;
	}

}
