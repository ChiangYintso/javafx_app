package ex.ch09.ex0901;

public class DivideZero {
	public static void main(String[] args) {
		int x = 0;
		int y = 0;
		try {
			y = 3 / x;
		} catch (Exception e) {
			System.out.println("运行时异常");
		}
		System.out.println("程序结束");
	}
}
