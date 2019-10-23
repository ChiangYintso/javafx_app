package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;

public class ScheduleUI extends UI {
	
	enum MENU implements AbstractMenu {
		EXIT(AbstractMenu.EXIT);
		
		private String menuClassName;
		MENU(String menuClassName) {
			this.menuClassName = menuClassName;
		}
		@Override
		public String getMenuClassName() {
			return this.menuClassName;
		}
	}
	
	public ScheduleUI() {
		
	}
	
	@Override
	public AbstractMenu showUI() {
		System.out.println("scheduleui");
		System.out.println("-------------");
		System.out.println("0. ·µ»Ø");
		System.out.println("-------------");
		
		return MENU.EXIT;
	}

	@Override
	protected void setSelectedMenuMap() {
		// TODO Auto-generated method stub
		
	}
}
