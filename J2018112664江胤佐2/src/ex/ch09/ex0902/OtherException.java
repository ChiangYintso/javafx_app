package ex.ch09.ex0902;

public class OtherException {
	public static void main(String[] args) {
		try {
			int[] a = null;
			a[0] = 1;
		} catch (NullPointerException e) {
			System.out.println("��ָ���쳣");
		}
		try {
			String str = null;
			str.length();
		} catch (NullPointerException e) {
			System.out.println("��ָ���쳣");
		}
		try {
			Object obj = new Object();
			String str = (String)obj;
		} catch (ClassCastException e) {
			System.out.println("ǿ������ת���쳣");
		}
	}
}
