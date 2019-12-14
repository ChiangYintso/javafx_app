package ex.ch13.ex1301;

@FunctionalInterface
interface StringFunc {
    public String func(String s);
}

public class AnotationDemo {
    String name;
    int age;

    @Override
    public String toString() {
        return "AnotationDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Deprecated
    public void show(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) {
        AnotationDemo p = new AnotationDemo();
        p.show("уехЩ");
    }
}
