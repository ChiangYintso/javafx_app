package ex.ch18.ex1803;

import java.io.*;
import java.net.Socket;

/**
 * @author Jiang Yinzuo
 */
public class MyClient {
    public static void main(String[] args) {
        String str;
        try (Socket soc = new Socket("localhost", 5656);
             PrintWriter cout = new PrintWriter(soc.getOutputStream(), true);
             InputStreamReader cin = new InputStreamReader(soc.getInputStream());
             BufferedReader br = new BufferedReader(cin);
             BufferedReader cbr = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                str = cbr.readLine();
                cout.println(str);
                cout.flush();
                if (!"bye".equals(str)) {
                    System.out.println("服务器说" + br.readLine());
                } else {
                    System.out.println("连接关闭");
                }
            } while (!"bye".equals(str));
        } catch (IOException ignored) {}
    }
}
