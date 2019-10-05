package ex.ch04.ex0403;

public class Dyadic {

	public static void main(String[] args) {
		int i, j;
		int[][] a;
		a = new int[2][3];
		for (i = 0; i < 2; ++i) {
			for (j = 0; j < 3; ++j) {
				a[i][j] = (int)(Math.random() * 100) + 1;
				System.out.print("a[" + i + "]" + "[" + j + "]=" +a[i][j] + " ");
			}
		}
	}

}
