package ex.ch04.ex0402;

import java.util.Scanner;

public class LinearB {

	public static void main(String[] args) {
		int i, max, min;
		int[] a = new int[5];
		Scanner reader = new Scanner(System.in);
		
		System.out.println("input 5 numbers:");
		for (i = 0; i < 5; ++i) {
			a[i] = reader.nextInt();
		}
		max = a[0];
		min = a[0];
		for (i = 0; i < 5; ++i) {
			max = max > a[i] ? max : a[i];
			min = min < a[i] ? min : a[i];
		}
		System.out.println("最大的数是：" + max);
		System.out.println("最小的数是：" + min);
		reader.close();
	}

}
