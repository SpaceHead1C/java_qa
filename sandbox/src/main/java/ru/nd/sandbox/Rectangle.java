package ru.nd.sandbox;

public class Rectangle {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getArea() {
        return a * b;
    }

    public double getWidth() {
        return a;
    }

    public double getHeight() {
        return b;
    }
}
