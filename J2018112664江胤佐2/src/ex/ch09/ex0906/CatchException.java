package ex.ch09.ex0906;

public class CatchException {
	public static void main(String[] args) {
		System.out.println("����ʼ");
		try {
			System.in.read();
		} catch (Exception e) {
			
		}
		System.out.println("�������");
	}
}
