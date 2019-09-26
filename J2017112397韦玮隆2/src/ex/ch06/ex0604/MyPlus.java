package ex.ch06.ex0604;

public class MyPlus {

	public int plus(int a, int b)
	{
		int s;
		s = a + b;
		return s;
	}
	
	public int plus(int a, int b, int c)
	{
		int s;
		s = plus(a,b) + c;
		return s;
	}
	
	public int plus(int a, int b, int c, int d)
	{
		int s;
		s = plus(a,b,c) + d;
		return s;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyPlus p = new MyPlus();
		System.out.println(p.plus(1, 2));
		System.out.println(p.plus(1, 2, 3));
		System.out.println(p.plus(1, 2, 3, 4));
	}

}
