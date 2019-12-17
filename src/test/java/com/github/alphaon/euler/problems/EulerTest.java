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

}
