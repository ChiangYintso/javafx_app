package ex.ch09.ex0901;

public class DivideZero {
	public static void main(String[] args) {
		int x = 0;
		int y = 0;
		try {
			y = 3 / x;
		} catch (Exception e) {
			System.out.println("����ʱ�쳣");
		}
		System.out.println("�������");
	}
}
