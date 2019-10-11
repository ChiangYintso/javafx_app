package ex.ch07.ex0704;

abstract class Shape {
	abstract public float area();
	abstract public void printArea();
}

class Rectangle extends Shape {
	
	int width;
	int length;
	Rectangle(int newWidth, int newLength) {
		width = newWidth;
		length = newLength;
	}
	public float area() {
		return width*length;
	}
	public void printArea() {
		System.out.println(area());
	}
}

class Circle extends Shape {
	final float PI = 3.14F;
	int radius;
	public Circle(int newRadius) {
		radius = newRadius;
	}
	public float area() {
		return PI * radius * radius;
	}
	public void printArea() {
		System.out.println(area());
	}
}

class ChouXiang {
	public static void main(String[] args) {
		Rectangle s1 = new Rectangle(3, 4);
		Circle s2 = new Circle(2);
		s1.printArea();
		s2.printArea();
	}
}
