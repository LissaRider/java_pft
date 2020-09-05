package ru.stqa.pft.sandbox;

public class Equation {
  private final double a;
  private final double b;
  private final double c;
  private final int n;

  public Equation(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
    double d = Math.pow(b, 2) - 4 * a * c;

    if (a != 0) {
      if (d > 0) {
        n = 2;
        System.out.println("Решений 2.");
      } else if (d == 0) {
        n = 1;
        System.out.println("Решений 1.");
      } else {
        n = 0;
        System.out.println("Решений 0.");
      }
    } else if (b != 0) {
      n = 1;
      System.out.println("Решений 1.");
    } else if (c != 0) {
      n = 0;
      System.out.println("Решений 0.");
    } else {
      n = -1;
      System.out.println("0 = 0. Решений бесконечно много. Решать данное уравнение бессмысленно.");
    }
  }

  public int rootNumber() {
    return n;
  }
}
