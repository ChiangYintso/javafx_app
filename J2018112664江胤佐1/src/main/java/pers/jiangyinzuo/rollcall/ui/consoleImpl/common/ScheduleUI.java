package main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.common;

import java.util.HashMap;
import java.util.Map;

import main.java.pers.jiangyinzuo.rollcall.ui.AbstractMenu;
import main.java.pers.jiangyinzuo.rollcall.ui.UI;
import main.java.pers.jiangyinzuo.rollcall.ui.consoleImpl.util.Select;

public class ScheduleUI implements UI {

	private Map<String, AbstractMenu> selectMap = new HashMap<>();
	
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
	
	@Override
	public AbstractMenu showUI() {
		System.out.println("-------------");
		System.out.println("0. ·µ»Ø");
		System.out.println("-------------");
		
		
		
		Select.selectMenu(selectMap);
		return MENU.EXIT;
	}
	
	public ScheduleUI() {
		
	}
}
