package ex.ch10.ex1006;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BaseData {
	public static void main(String[] args) throws IOException {
		FileInputStream fin;
		FileOutputStream fout;
		DataInputStream din;
		DataOutputStream dout;
		File f = new File("./src/ex/ch10/ex1006/baseData.txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
		
		}
		try {
			fout = new FileOutputStream(f);
			dout = new DataOutputStream(fout);
			dout.writeInt(10);
			dout.writeLong(12345L);
			dout.writeFloat(3.1415926f);
			dout.writeDouble(987654321.123d);
			dout.writeBoolean(true);
			dout.close();
		} catch (IOException e) {
			
		}
		try {
			fin = new FileInputStream(f);
			din = new DataInputStream(fin);
			System.out.println(din.readInt());
			System.out.println(din.readLong());
			System.out.println(din.readFloat());
			System.out.println(din.readDouble());
			System.out.println(din.readBoolean());
			din.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			
		}
	}
}
