package ex.ch12.ex1202;

import java.sql.ResultSet;

public class Cylinder<E> {
    E bottom;
    double height;

    public Cylinder(E bottom, double height) {
        this.bottom = bottom;
        this.height = height;
    }

    public double volume() {
        return Double.parseDouble(bottom.toString()) * height;
    }

    @Override
    public String toString() {
        return " " + volume();
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(12, 8.5);
        Cylinder<Double> cylinder = new Cylinder<>(12.4, 8.5);
        System.out.println(rectangle.toString() + cylinder.toString());

        Circle circle = new Circle(23.3);
        Cylinder<Double> cylinder1 = new Cylinder<>(24.3, 32);
        System.out.println(circle.toString() + cylinder1.toString());
    }
}

class Circle {
    double radius, area;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        area = 3.14 * radius * radius;
        return " " + area;
    }

}

class Rectangle {
    double length, width, area;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public String toString() {
        area = width * length;
        return " " + area;
    }
}