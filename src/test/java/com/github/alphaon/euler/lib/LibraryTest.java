package com.github.alphaon.euler.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LibraryTest {
    @Test
    void testIsPalydromLong() {
        var Lib = Library.newInstance();
        Assertions.assertTrue(Lib.isPalyndrom(121), "121 is palyndrom");
        Assertions.assertTrue(Lib.isPalyndrom(-12345677654321L), "-12345677654321L is palyndrom");
        Assertions.assertTrue(Lib.isPalyndrom(0), "0 is palyndrom");
        Assertions.assertFalse(Lib.isPalyndrom(12332), "12332 is not palyndrom");
    }
}