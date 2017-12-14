package ru.nd.sandbox;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
