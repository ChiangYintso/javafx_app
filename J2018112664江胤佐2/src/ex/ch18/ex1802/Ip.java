package ex.ch18.ex1802;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Jiang Yinzuo
 */
public class Ip {
    InetAddress myIPaddress = null;
    InetAddress myServer = null;

    public static void main(String[] args) throws UnknownHostException {
        InetAddress myIP;
        InetAddress baiduIP;
        myIP = InetAddress.getLocalHost();
        baiduIP = InetAddress.getByName("www.baidu.com");
        System.out.println("�ҵĵ�ַΪ��" + myIP);
        System.out.println("�ٶȵĵ�ַΪ��" + baiduIP);
    }
}

