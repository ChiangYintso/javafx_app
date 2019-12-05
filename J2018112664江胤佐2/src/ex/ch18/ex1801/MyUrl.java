package ex.ch18.ex1801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jiang Yinzuo
 */
public class MyUrl {
    public static void main(String[] args) throws IOException {
        String urlName = "http://www.baidu.com/index.html";
        new MyUrl().display(urlName);
    }

    public void display(String urlName) throws IOException {
        URL url;
        try {
            url = new URL(urlName);
            System.out.println("Э������" + url.getProtocol());
            System.out.println("��������" + url.getHost());
            System.out.println("�˿ںţ�" + url.getPort());
            System.out.println("�ļ�����" + url.getFile());
            InputStreamReader in = new InputStreamReader(url.openStream());
            BufferedReader br = new BufferedReader(in);
            String aline;
            while ((aline = br.readLine()) != null) {
                System.out.println(aline);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

