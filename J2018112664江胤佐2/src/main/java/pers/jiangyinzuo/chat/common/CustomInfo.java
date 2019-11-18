package main.java.pers.jiangyinzuo.chat.common;

/**
 * 自定义消息
 * @author Jiang Yinzuo
 *
 */
public class CustomInfo {
	
	// 状态码
	private short status;
	
	// 错误信息
	private String errInfo;
	
	public CustomInfo(short status, String errInfo) {
		this.status = status;
		this.errInfo = errInfo;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
}
