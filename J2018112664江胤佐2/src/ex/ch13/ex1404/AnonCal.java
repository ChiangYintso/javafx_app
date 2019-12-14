package ex.ch13.ex1404;

import java.util.Calendar;

interface Calculable {
    int calInt(int a, int b);
}

/**
 * @author Jiang Yinzuo
 */
public class AnonCal {
    public static Calculable cal(char opr) {
        Calculable result;
        if (opr == '+') {
            result = new Calculable() {
                @Override
                public int calInt(int a, int b) {
                    return a+b;
                }
            };
        } else {
            result = new Calculable() {
                @Override
                public int calInt(int a, int b) {
                    return a-b;
                }
            };
        }
        return result;
    }

    public static void main(String[] args) {
        int n1 = 10, n2 = 5;
        Calculable f1 = cal('+');
        System.out.println(f1.calInt(10, 5));
        Calculable f2 = cal('-');
        System.out.println(f2.calInt(10, 5));
    }
}
