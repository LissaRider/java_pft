package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Расстояние между двумя точками p1(x1;y1) и p2(x2;y2) в прямоугольной системе координат выражается формулой:
  // d = Math.sqrt(Math.pow(x2−x1, 2) + Math.pow(y2−y1, 2)
  // Метод Math.pow(double base, double exponent) - возведение в степень
  // Метод Math.sqrt(double base) — возвращает квадратный корень из аргумента
  public double distance(Point p) {
    return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
  }
}