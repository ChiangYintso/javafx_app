package ex.ch09.ex0902;

public class OtherException {
	public static void main(String[] args) {
		try {
			int[] a = null;
			a[0] = 1;
		} catch (NullPointerException e) {
			System.out.println("空指针异常");
		}
		try {
			String str = null;
			str.length();
		} catch (NullPointerException e) {
			System.out.println("空指针异常");
		}
		try {
			Object obj = new Object();
			String str = (String)obj;
		} catch (ClassCastException e) {
			System.out.println("强制类型转换异常");
		}
	}
}
