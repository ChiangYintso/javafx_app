package ex.ch09.ex0903;

public class ManyException {
	public static void main(String[] args) {
		int i;
		int a[] = {1, 2, 3, 4};
		for (i = 0; i < 5; ++i) {
			try {
				System.out.println("a[" + i + "]/" + i + "=" + (a[i]/i));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("�����������±�Խ���쳣");
			} catch (ArithmeticException e) {
				System.out.println("�쳣���������" + e);
				
			} catch (Exception e) {
				System.out.println("����" + e.getMessage() + "�쳣!");
				
			} finally {
				System.out.println("finally i = " + i);
			}
		}
		System.out.println("����");
	}
}
