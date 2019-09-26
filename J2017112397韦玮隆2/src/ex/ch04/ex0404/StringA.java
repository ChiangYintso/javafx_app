package ex.ch04.ex0404;

public class StringA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = new String("Hello");
		String str4 = new String("Hello");
		System.out.println("用运算符==进行比较结果如下：");
		if(str1 == str2)
			System.out.println("str1等于str2");
		else
			System.out.println("str1不等于str2");
		
		if(str3 == str4)
			System.out.println("str3等于str4");
		else
			System.out.println("str3不等于str4");
		
		if(str2 == str3)
			System.out.println("str2等于str3");
		else
			System.out.println("str2不等于str3");
		
		System.out.println("调用equals()方法进行比较结果如下：");
		if(str1.equals(str2))
			System.out.println("str1等于str2");
		else
			System.out.println("str1不等于str2");
		
		if(str3.equals(str4))
			System.out.println("str3等于str4");
		else
			System.out.println("str3不等于str4");
		
		if(str2.equals(str3))
			System.out.println("str2等于str3");
		else
			System.out.println("str2不等于str3");
	}

}
