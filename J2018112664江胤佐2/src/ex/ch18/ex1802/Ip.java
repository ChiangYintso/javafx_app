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
        System.out.println("我的地址为：" + myIP);
        System.out.println("百度的地址为：" + baiduIP);
    }
}

