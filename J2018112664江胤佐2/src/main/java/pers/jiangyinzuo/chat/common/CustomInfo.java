package main.java.pers.jiangyinzuo.chat.common;

/**
 * �Զ�����Ϣ
 * @author Jiang Yinzuo
 *
 */
public class CustomInfo {
	
	// ״̬��
	private short status;
	
	// ������Ϣ
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
