package ex.ch18.ex1804;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author Jiang Yinzuo
 */
public class UDPBaJie {
    public static void main(String[] args) {
        InetAddress address;
        DatagramPacket dp;
        DatagramSocket ds;
        String str;
        Scanner sc = new Scanner(System.in);
        Thread th;
        ReceiveBaJie rec = new ReceiveBaJie();
        try {
            th = new Thread(rec);
            th.start();
            byte[] buf = new byte[256];
            address = InetAddress.getByName("localhost");
            dp = new DatagramPacket(buf, buf.length, address, 8081);
            ds = new DatagramSocket();
            System.out.println("猴哥你听着");
            while (sc.hasNext()) {
                str = sc.nextLine();
                buf = str.getBytes();
                if (str.length() == 0) {
                    System.exit(0);
                }
                buf = str.getBytes();
                dp.setData(buf);
                ds.send(dp);
            }
        } catch (IOException ignored) {}
    }
}

class ReceiveBaJie implements Runnable {

    @Override
    public void run() {
        DatagramPacket dp = null;
        DatagramSocket ds = null;
        byte[] data = new byte[256];
        try {
            dp = new DatagramPacket(data, data.length);
            ds = new DatagramSocket(6969);
        } catch (SocketException ignored) {}
        while (true) {
            if (ds == null) {
                break;
            } else {
                try {
                    ds.receive(dp);
                    String str = new String(dp.getData(), 0, dp.getLength());
                    System.out.println("我八戒收到的是:" + str);
                } catch (IOException ignored) {}
            }
        }
    }
}