package ex.ch18.ex1804;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author Jiang Yinzuo
 */
public class UDPWuKong {
    public static void main(String[] args) {
        InetAddress address;
        DatagramPacket dp;
        DatagramSocket ds;
        String str;
        Scanner sc = new Scanner(System.in);
        Thread th;
        ReceiveWuKong rec = new ReceiveWuKong();
        try {
            th = new Thread(rec);
            th.start();
            byte[] buf = new byte[256];
            address = InetAddress.getByName("localhost");
            dp = new DatagramPacket(buf, buf.length, address, 6969);
            ds = new DatagramSocket();
            System.out.println("八戒你听着");
            while (sc.hasNext()) {
                str = sc.nextLine();
                buf = str.getBytes();
                if (str.length() == 0) {
                    System.exit(0);
                }
                buf = str.getBytes();
                dp.setData(buf);
                ds.send(dp);
                System.out.println("八戒你接着听");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiveWuKong implements Runnable {

    @Override
    public void run() {
        DatagramPacket dp = null;
        DatagramSocket ds = null;
        byte[] data = new byte[256];
        try {
            dp = new DatagramPacket(data, data.length);
            ds = new DatagramSocket(8081);
        } catch (SocketException ignored) {
        }
        assert ds != null;
        while (!ds.isClosed()) {
            try {
                ds.receive(dp);
                System.out.println("我猴哥收到的是: " + new String(dp.getData(), 0, dp.getLength()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
