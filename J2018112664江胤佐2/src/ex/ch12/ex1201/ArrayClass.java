package ex.ch12.ex1201;

import java.util.ArrayList;
import java.util.List;

public class ArrayClass<T> {
    private List<T> array;
    public void setT(List<T> array) {
        this.array = array;
    }
    public List<T> getT() {
        return this.array;
    }

    public static void main(String[] args) {
        ArrayClass<String> arr = new ArrayClass<>();
        List<String> beauty = new ArrayList<>();

        beauty.add("Î÷Ê©");
        beauty.add("ÍõÕÑ¾ı");
        beauty.add("Ñî¹óåú");
        beauty.add("õõ²õ");
        arr.setT(beauty);
        for (String str : arr.getT()) {
            System.out.println(str);
        }
    }
}
