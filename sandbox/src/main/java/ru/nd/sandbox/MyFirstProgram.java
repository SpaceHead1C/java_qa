package ru.nd.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("World");
        hello("Andy");

        Square s = new Square(5);
        System.out.println("Area of square with length " + s.getLength() + " = " + s.getArea());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Area of rectangle with width " + r.getWidth() + " and height " + r.getHeight() + " = " + r.getArea());

        Point p1 = new Point(3, 6);
        Point p2 = new Point(9, 1);
        System.out.println("Distance between " + p1 + " and " + p2 + " = " + Point.distance(p1, p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}