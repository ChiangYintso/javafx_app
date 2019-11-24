package main.java.pers.jiangyinzuo.rollcall.ui.console.common;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.AbstractUi;

public class ScheduleUi extends AbstractUi {
	
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
	
	public ScheduleUi() {
		
	}
	
	@Override
	public AbstractMenu showUi() {
		System.out.println("scheduleui");
		System.out.println("-------------");
		System.out.println("0. ·µ»Ø");
		System.out.println("-------------");
		
		return MENU.EXIT;
	}

	@Override
	protected void setSelectedMenuMap() {
		
	}
}
