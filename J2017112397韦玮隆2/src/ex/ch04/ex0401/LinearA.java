package ex.ch04.ex0401;

public class LinearA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		int[] a;
		a = new int[10];
		for(i=0;i<a.length;i++)
			System.out.print("a[" + i + "]=" + a[i] +" ");
		System.out.println("\n");
		for(i=a.length-1;i>=0;i--)
		{
			a[i] = i;
			System.out.println("a[" + i + "]=" + a[i] +" ");
		}
	}

}
