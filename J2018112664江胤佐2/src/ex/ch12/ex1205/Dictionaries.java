package ex.ch12.ex1205;

import java.util.HashMap;
import java.util.Map;

public class Dictionaries {
    public static void main(String[] args) {
        String[] cw = {"音乐", "舞蹈", "小汽车", "男人", "女人", "男孩", "女孩"};
        String[] ew = {"music", "dance", "car", "man", "woman", "boy", "girl"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < cw.length; ++i) {
            map.put(cw[i], ew[i]);
        }
        System.out.println("共有" + cw.length + "个单词");
        System.out.println(map);
        System.out.println(map.get("男人"));
        map.remove("小汽车");
        for (String s : map.keySet()) {
            System.out.println(s);
        }
    }
}
