package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {

  @Test
  public void positiveTestArea() {
    Rectangle r = new Rectangle(4,6);
    Assert.assertEquals(r.area(), 24.0);
  }

  @Test
  public void negativeTestArea() {
    Rectangle r = new Rectangle(4,6);
    Assert.assertNotEquals(r.area(), 27.0);
  }
}
