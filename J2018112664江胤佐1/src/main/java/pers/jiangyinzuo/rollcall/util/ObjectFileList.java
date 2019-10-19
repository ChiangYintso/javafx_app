package main.java.pers.jiangyinzuo.rollcall.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectFileList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Object> list = new ArrayList<>();
	
	public ObjectFileList(Object[] objectList) {
		for (Object obj : objectList) {
			list.add(obj);
		}
	}
	
	public boolean concat(ObjectFileList o) {
		return list.addAll(o.list);
	}
}
