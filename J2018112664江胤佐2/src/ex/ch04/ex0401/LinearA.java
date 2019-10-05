package ex.ch04.ex0401;

public class LinearA {
	public static void main(String[] args) {
		int i;
		int[] a;
		a = new int[10];
		
		for (i = 0; i < a.length; ++i) {
			System.out.print("a[" + i + "]=" + a[i] + " ");
		}
		System.out.print("\n");
		for (i = a.length - 1; i >= 0; --i) {
			a[i] = i;
			System.out.print("a[" + i + "]=" + a[i] + " ");
		}
	}
}
