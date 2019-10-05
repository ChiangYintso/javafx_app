package ex.ch03.ex0306;

public class DoWhileSentence {

	public static void main(String[] args) {
		int i = 100, s = 0;
		do {
			s += i;
			--i;
		} while (i > 0);
		System.out.println("100 + 99 + 98 + ... + 1 = " + s);
	}

}
