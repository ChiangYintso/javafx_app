package ex.ch02.ex0205;

public class Conver {
	public static void main(String[] args) {
		byte a = 0;
		short b = 0;
		int c = 0;
		long d = 0;
		float e = 0F;
		double f = 0D;
		
		String s = "10";
		
		b = Byte.parseByte(s);
		c = Integer.parseInt(s);
		d = Long.parseLong(s);
		e = Float.parseFloat(s);
		f = Double.parseDouble(s);
		
		int myInt = 1234;
		String myString = "" + myInt;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);

		System.out.println(myString);
	}
}
