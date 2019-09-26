package ex.ch06.ex0605;

class Ca
{
	public static int b;
}

public class Cb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ca s1 = new Ca();
		Ca s2 = new Ca();
		Ca.b = 5;
		s1.b = 6;
		s2.b = 7;
		System.out.println("Ca.b=" + Ca.b);
		System.out.println("s1.b=" + s1.b);
		System.out.println("s2.b=" + s2.b);
	}

}
