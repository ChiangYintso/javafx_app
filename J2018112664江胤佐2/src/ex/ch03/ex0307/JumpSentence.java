package ex.ch03.ex0307;

public class JumpSentence {
	public static void main(String[] args) {
		for (int i = 1; i < 10; ++i) {
			if (i == 6) {
				break;
			}
			System.out.println(" " + i);
		}
		System.out.println("显示完毕");
		for (int i = 1; i < 10; ++i) {
			if (i % 2 == 0) {
				continue;
			}
			System.out.println(" " + i);
		}
		System.out.println("显示完毕");
	}
}
