package ex.ch09.ex0903;

public class ManyException {
	public static void main(String[] args) {
		int i;
		int a[] = {1, 2, 3, 4};
		for (i = 0; i < 5; ++i) {
			try {
				System.out.println("a[" + i + "]/" + i + "=" + (a[i]/i));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("捕获到了数组下标越界异常");
			} catch (ArithmeticException e) {
				System.out.println("异常类的名称是" + e);
				
			} catch (Exception e) {
				System.out.println("捕获" + e.getMessage() + "异常!");
				
			} finally {
				System.out.println("finally i = " + i);
			}
		}
		System.out.println("继续");
	}
}
