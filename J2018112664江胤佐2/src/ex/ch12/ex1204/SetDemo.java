package ex.ch12.ex1204;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        int[] a = {2, 5, 9, 13};
        int[] b = {1, 3, 6, 15, 21};
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for (int i = 0; i < a.length; ++i) {
            setA.add(a[i]);
        }
        for (int i = 0; i < b.length; ++i) {
            setB.add(b[i]);
        }
        System.out.println("合并前a的数据");
        for (Integer i : setA) {
            System.out.print(i + " ");
        }
        System.out.println("");
        setA.addAll(setB);
        System.out.println("合并后a的数据");
        for (Integer i : setA) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }
}
