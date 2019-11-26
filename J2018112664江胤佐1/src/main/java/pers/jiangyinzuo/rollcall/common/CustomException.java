package pers.jiangyinzuo.rollcall.common;

public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7691595303103866753L;

	private String errInfo;
	private boolean isOk;
	
	public CustomException(String errInfo, boolean isOk) {
		this.errInfo = errInfo;
		this.isOk = isOk;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
