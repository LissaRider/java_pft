package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TwoPointsTests {

  @Test
  public void testDistance0() {
    Point p1 = new Point(4, 5);
    Point p2 = new Point(8, 10);
    Assert.assertEquals(p2.distance(p1), 6.4031242374328485);
  }

  @Test
  public void testDistance1() {
    Point p1 = new Point(4, 5);
    Point p2 = new Point(8, 10);
    Assert.assertNotEquals(p2.distance(p1), 6.4);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(4, 5);
    Point p2 = new Point(8, 10);
    double d1 = p1.distance(p2);
    double d2 = p2.distance(p1);
    Assert.assertTrue(d1 == d2,
            "\n Внимание! Расстояние от точки p1 до точки p2 не совпадает " +
                    "с расстоянием от точки p2 до точки p1.\n");
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(8, 10);
    Assert.assertEquals((int)p1.distance(p2), 12);
  }

  @Test
  public void testDistance4() {
    Point p1 = new Point(0, 1);
    Point p2 = new Point(2, -2);
    Assert.assertEquals(p2.distance(p1), Math.sqrt(13));
  }

  @Test
  public void testDistance5() {
    Point p1 = new Point(-1, 3);
    Point p2 = new Point(6, 2);
    double expected = 5 * Math.sqrt(2.0);
    Assert.assertEquals(p2.distance(p1), expected);
  }

  @Test
  public void testDistance6() {

    Point p1 = new Point(2, -5);
    Point p2 = new Point(-4, 3);
    double d = p1.distance(p2);
    Assert.assertTrue( d > 9,
            String.format("\n Ошибка! Полученное расстояние между точками %d меньше 9.\n", (int)d));
  }

  @Test
  public void testDistance7() {
    int x = 0;
    Point p1 = new Point(1, -1);
    Point p = new Point(x, 1.3);
    x = 3;
    double d1 = p1.distance(p);
    x = -3;
    double d2 = p1.distance(p);
    Assert.assertTrue(d1 == d2,
            "\n Ошибка! Расстояния от точки p1 до двух параллельных точек не совпадают.\n");
  }

  @Test
  public void testDistance8() {
    Point p = new Point(2,-1);
    Point p1 = new Point(7, -1);
    Point p2 = new Point(-2, 2);
    Point p3 = new Point(-1, -5);
    double d1 = p.distance(p1);
    double d2 = p.distance(p2);
    double d3 = p.distance(p3);
    Assert.assertTrue(d1 == d2 && d1 == d3 && d2 == d3,
            "\n Ошибка! Точка p не равноудалена от точек p1, p2 и p3.\n");
  }
}