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
        String[] chinese = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五" , "星期六"};
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new TreeMap<>();
        for (int i = 0; i < english.length; ++i) {
            map.put(english[i], chinese[i]);
            map2.put(english[i], chinese[i]);
        }
        System.out.println("HashMap是无序的, TreeMap是有序的");
        for (Map.Entry<String, String> kv : map.entrySet()) {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
        for (Map.Entry<String, String> kv : map2.entrySet()) {
            System.out.println(kv.getKey() + ": " + kv.getValue());
        }
    }
}
