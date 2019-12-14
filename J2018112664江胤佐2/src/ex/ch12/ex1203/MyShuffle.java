package ex.ch12.ex1203;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class MyShuffle {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            int n = (int) (Math.random() * 100 + 1);
            list.add(n);
        }

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            System.out.print(num + " ");
        }
        System.out.println("");

        Collections.shuffle(list);
        System.out.println("Ï´ÅÆºóµÄË³Ðò");
        it = list.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            System.out.print(num + " ");
        }
        System.out.println("");

        Collections.rotate(list, 2);
        it = list.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            System.out.print(num + " ");
        }
        System.out.println("");
    }
}
