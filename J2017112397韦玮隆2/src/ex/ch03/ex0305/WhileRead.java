package ex.ch03.ex0305;

import java.io.*;

public class WhileRead 
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		int count = 0, b;
		System.out.print("请输入数据：");
		while((b=(char)System.in.read()) != '\n' && (b=(char)System.in.read()) != '\r')
		{
			System.out.print((char)b);
			count++;
		}
		System.out.print("\n您输入了" + count + "个字符");
	}

}
