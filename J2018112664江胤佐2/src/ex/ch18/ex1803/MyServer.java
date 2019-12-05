package ex.ch18.ex1803;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jiang Yinzuo
 */
public class MyServer {
    public static void main(String[] args) {
        String str;
        try (ServerSocket ser = new ServerSocket(5656);
             Socket soc = ser.accept();
             InputStreamReader sin= new InputStreamReader(soc.getInputStream());
             PrintWriter sout = new PrintWriter(soc.getOutputStream());
             BufferedReader br = new BufferedReader(sin);
             BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (!"bye".equals(str = br.readLine())) {
                System.out.println("¿Í»§Ëµ: " + str);
                System.out.println(sbr.readLine());
                sout.flush();
            }
        } catch (IOException ignored) {}
    }
}
