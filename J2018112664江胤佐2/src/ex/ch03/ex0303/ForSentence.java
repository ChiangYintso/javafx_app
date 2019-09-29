package ex.ch03.ex0303;

public class ForSentence {
	public static void main(String[] args) {
		int s = 0;
		for (int i = 1; i <= 99; ++i) {
			s += i;
			++i;
		}
		System.out.println("1 + 3 + 5 + ... + 99 = " + s);
	}
}
