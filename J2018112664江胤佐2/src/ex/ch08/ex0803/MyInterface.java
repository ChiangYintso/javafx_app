package ex.ch08.ex0803;

import java.text.DecimalFormat;

interface Shape {
	final float PI = 3.14F;
	double area();
	double volume();
}

class Cylinder implements Shape {
	private double radius;
	private int height;
	public Cylinder(double r, int h) {
		radius = r;
		height = h;
	}
	
	public double area() {
		return PI * radius * radius;
	}
	
	public double volume() {
		return area() * height;
	}
}

class MyInterface {
	public static void main(String[] args) {
		Cylinder a = new Cylinder(2, 3);
		DecimalFormat myFormat = new DecimalFormat("0.00");
		System.out.println("圆柱体的底面积是" + myFormat.format(a.area()));
		System.out.println("圆柱体的体积是" + myFormat.format(a.volume()));
	}
}
