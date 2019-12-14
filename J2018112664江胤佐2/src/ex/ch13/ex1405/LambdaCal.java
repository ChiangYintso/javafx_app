package ex.ch13.ex1405;

interface Calculable {
    int calInt(int a, int b);
}

public class LambdaCal {
    public static Calculable cal(char opr) {
        Calculable result;
        if (opr == '+') {
            result = (a, b) -> a+b;
        } else {
            result = (a, b) -> a-b;
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
