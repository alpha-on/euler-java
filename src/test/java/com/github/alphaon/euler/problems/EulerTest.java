package com.github.alphaon.euler.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// See: https://github.com/nayuki/Project-Euler-solutions/blob/master/Answers.txt
public class EulerTest {
    @Test
    void testP001() {
        Assertions.assertEquals("233168", new P001().run());
    }

    @Test
    void testP002() {
        Assertions.assertEquals("4613732", new P002().run());
    }

    @Test
    void testP003() {
        Assertions.assertEquals("6857", new P003().run());
    }

    @Test
    void testP004() {
        Assertions.assertEquals("906609", new P004().run());
    }

    @Test
    void testP005() {
        Assertions.assertEquals("232792560", new P005().run());
    }

    @Test
    void testP006() {
        Assertions.assertEquals("25164150", new P006().run());
    }

    @Test
    void testP007() {
        Assertions.assertEquals("104743", new P007().run());
    }

    @Test
    void testP008() {
        Assertions.assertEquals("23514624000", new P008().run());
    }

    @Test
    void testP009() {
        Assertions.assertEquals("31875000", new P009().run());
    }

    @Test
    void testP010() {
        Assertions.assertEquals("142913828922", new P010().run());
    }

    @Test
    void testP011() {
        Assertions.assertEquals("70600674", new P011().run());
    }

    @Test
    void testP012() {
        Assertions.assertEquals("76576500", new P012().run());
    }

    @Test
    void testP013() {
        Assertions.assertEquals("5537376230", new P013().run());
    }

    @Test
    void testP014() {
        Assertions.assertEquals("837799", new P014().run());
    }

    @Test
    void testP015() {
        Assertions.assertEquals("137846528820", new P015().run());
    }

    @Test
    void testP016() {
        Assertions.assertEquals("1366", new P016().run());
    }

    @Test
    void testP017() {
        Assertions.assertEquals("21124", new P017().run());
    }

    @Test
    void testP018() {
        Assertions.assertEquals("1074", new P018().run());
    }

}
