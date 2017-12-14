package ru.nd.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EqualsTests {
    @Test
    public void test0() {
        Equation eq = new Equation(1, 1, 1);
        Assert.assertEquals(eq.rootNumber(), 0);
    }

    @Test
    public void test1() {
        Equation eq = new Equation(1, 2, 1);
        Assert.assertEquals(eq.rootNumber(), 1);
    }

    @Test
    public void test2() {
        Equation eq = new Equation(1, 5, 6);
        Assert.assertEquals(eq.rootNumber(), 2);
    }
}
