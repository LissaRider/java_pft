package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    // hello("world");
    // hello("user");
    // hello("Alexei");

    // Square s = new Square(5);
    // System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    // Rectangle r = new Rectangle(4, 6);
    // System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    // Точка p1
    Point p1 = new Point(4, 5);
    // Точка p2
    Point p2 = new Point(8, 10);
    System.out.println("Результат выполнения функции: " +
            "Расстояние между двумя точками p1(" + p1.x +";" + p1.y + ") " +
            "и p2(" + p2.x +";" + p2.y + ") = " + distance(p1, p2));

    System.out.println("Результат выполнения метода: " +
            "Расстояние между двумя точками p1(" + p1.x +";" + p1.y + ") " +
            "и p2(" + p2.x +";" + p2.y + ") = " + p2.distance(p1));
  }

  // public static void hello(String somebody) {
  //   System.out.println("Hello, " + somebody + "!");
  // }

  // Расстояние между двумя точками p1(x1;y1) и p2(x2;y2) в прямоугольной системе координат выражается формулой:
  // d = Math.sqrt(Math.pow(x2−x1, 2) + Math.pow(y2−y1, 2)
  // Метод Math.pow(double base, double exponent) - возведение в степень
  // Метод Math.sqrt(double base) — возвращает квадратный корень из аргумента
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }
}