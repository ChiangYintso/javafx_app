package ex.ch03.ex0307;

public class JumpSentence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1;i<10;i++)
		{
			if(i == 6)
				break;
			System.out.print("" + i);
		}
		System.out.println("\n 显示完毕");
		for(int i=1;i<10;i++)
		{
			if(i == 6)
				continue;
			System.out.print("" + i);
		}
		System.out.println("\n 显示完毕");
	}

}
