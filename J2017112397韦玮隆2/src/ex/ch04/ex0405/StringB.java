package ex.ch04.ex0405;

public class StringB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "Hello";
		String str2 = "World!";
		String str = str1 + str2;
		System.out.println("str=" + str);
		System.out.println("str�ĳ�����" + str.length());
		System.out.println("str�ĵڰ˸��ַ���" + str.charAt(7));
		System.out.println("str���ַ���or��һ�γ��ֵ�λ��" + str.indexOf("or"));
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
	}

}
