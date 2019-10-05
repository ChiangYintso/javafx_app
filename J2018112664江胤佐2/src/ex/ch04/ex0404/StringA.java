package ex.ch04.ex0404;

public class StringA {

	public static void main(String[] args) {
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = new String("Hello");
		String str4 = new String("Hello");
		System.out.println("用运算符 == 进行比较结果如下：");
		System.out.println(str1 == str2);
		System.out.println(str3 == str4);
		System.out.println(str2 == str3);
		System.out.println("调用equals()方法进行比较结果如下：");
		System.out.println(str1.equals(str2));
		System.out.println(str4.equals(str3));
		System.out.println(str3.equals(str2));
	}

}
