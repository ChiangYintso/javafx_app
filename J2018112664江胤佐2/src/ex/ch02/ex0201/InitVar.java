package ex.ch02.ex0201;

/**
 * 
 * @author 江胤佐
 * 
 * Java验证性实验，书P13
 */
public class InitVar {
	public static void main(String[] args) {
		byte a = 10;
		short b = 20;
		int c = 30;
		long d = 40L;
		float e = 50F;
		double f = 60;
		char g = 'A';
		boolean h = true;
		final double PI = 3.14;
		System.out.println("字节型,a=" + a);
		System.out.println("短整型,b=" + b);
		System.out.println("整数型,c=" + c);
		System.out.println("单精度型,d=" + d);
		System.out.println("双精度型,e=" + e);
		System.out.println("字符型,f=" + f);
		System.out.println("布尔型,g=" + g);
		System.out.println("圆周率,pi=" + PI);
	}
}
