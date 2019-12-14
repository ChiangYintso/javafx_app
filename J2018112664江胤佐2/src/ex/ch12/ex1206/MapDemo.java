package ex.ch12.ex1206;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jiang Yinzuo
 */
public class MapDemo {
    public static void main(String[] args) {
        String[] english = {"Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat"};
        String[] chinese = {"������", "����һ", "���ڶ�", "������", "������", "������" , "������"};
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new TreeMap<>();
        for (int i = 0; i < english.length; ++i) {
            map.put(english[i], chinese[i]);
            map2.put(english[i], chinese[i]);
        }
        System.out.println("HashMap�������, TreeMap�������");
        for (Map.Entry<String, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
        for (Map.Entry<String, String> kv : map2.entrySet()) {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
    }
}
