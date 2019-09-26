package ex.ch04.ex0402;

import java.util.*;

public class LinearB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,max,min;
		int[] a = new int[5];
		Scanner reader = new Scanner(System.in);
		for(i=0;i<a.length;i++)
		{
			a[i] = reader.nextInt();
		}
		max = a[0];
		min = a[0];
		for(i=0;i<5;i++)
		{
			max = a[i]>max?a[i]:max;
			min = a[i]<min?a[i]:min;
		}
		System.out.println("最大的数是" + max);
		System.out.println("最小的数是" + min);
	}
	

}
