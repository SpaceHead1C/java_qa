package ru.nd.sandbox;

public class Square {
    private double l;

    public Square(double l) {
        this.l = l;
    }

    public double getArea() {
        return l * l;
    }

    public double getLength() {
        return l;
    }
}
