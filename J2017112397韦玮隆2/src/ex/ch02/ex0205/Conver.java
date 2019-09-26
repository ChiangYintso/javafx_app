package ex.ch02.ex0205;

public class Conver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte a = 0;
		short b = 0;
		int c = 0;
		long d = 0;
		float e = 0F;
		double f = 0D;
		String s = "10";
		a = Byte.parseByte(s);
		b= Short.parseShort(s);
		c = Integer.parseInt(s);
		d = Long.parseLong(s);
		e = Float.parseFloat(s);
		f = Double.parseDouble(s);
		
		int MyInt = 1234;
		String MyString = "" + MyInt;
		
		System.out.println("a=" + a);
		System.out.println("b=" + b);
		System.out.println("c=" + c);
		System.out.println("d=" + d);
		System.out.println("e=" + e);
		System.out.println("f=" + f);
		System.out.println("MyString=" + MyString);
	}

}
