package com.github.alphaon.euler.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LibraryTest {
    @Test
    void testIsPalydromLong() {
        Assertions.assertTrue(new Library().isPalyndrom(121), "121 is palyndrom");
        Assertions.assertTrue(new Library().isPalyndrom(-12345677654321L), "-12345677654321L is palyndrom");
        Assertions.assertTrue(new Library().isPalyndrom(0), "0 is palyndrom");
        Assertions.assertFalse(new Library().isPalyndrom(12332), "12332 is not palyndrom");
    }
}