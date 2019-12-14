package ex.ch12.ex1205;

import java.util.HashMap;
import java.util.Map;

public class Dictionaries {
    public static void main(String[] args) {
        String[] cw = {"����", "�赸", "С����", "����", "Ů��", "�к�", "Ů��"};
        String[] ew = {"music", "dance", "car", "man", "woman", "boy", "girl"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < cw.length; ++i) {
            map.put(cw[i], ew[i]);
        }
        System.out.println("����" + cw.length + "������");
        System.out.println(map);
        System.out.println(map.get("����"));
        map.remove("С����");
        for (String s : map.keySet()) {
            System.out.println(s);
        }
    }
}
