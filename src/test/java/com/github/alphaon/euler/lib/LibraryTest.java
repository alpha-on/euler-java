package com.github.alphaon.euler.lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LibraryTest {
    @Test
    void testIsPalydromLong() {
        var Lib = Library.newInstance();
        Assertions.assertTrue(Lib.isPalyndrom(121), "121 is palyndrom");
        Assertions.assertTrue(Lib.isPalyndrom(-12345677654321L), "-12345677654321L is palyndrom");
        Assertions.assertTrue(Lib.isPalyndrom(0), "0 is palyndrom");
        Assertions.assertFalse(Lib.isPalyndrom(12332), "12332 is not palyndrom");
    }

    @Test
    void testPrimeFactors() {
        var Lib = Library.newInstance();
        Function<Integer, Object[]> primeFactors = N -> Streams.stream(() -> Lib.primeFactors(N)).toArray();
        Assertions.assertAll(
                () -> assertArrayEquals(new Tuple2[]{t2(2, 1), t2(5, 1)}, primeFactors.apply(10)),
                () -> assertArrayEquals(new Tuple2[]{t2(2, 3), t2(5, 1)}, primeFactors.apply(-40), "negative numbers prime factors"),
                () -> assertArrayEquals(new Tuple2[0], primeFactors.apply(0), "0 has no prime factor"),
                () -> assertArrayEquals(new Tuple2[0], primeFactors.apply(1), "1 has no prime factor"),
                () -> assertArrayEquals(new Tuple2[]{t2(2, 1)}, primeFactors.apply(2), "2 has 1 prime factor")
        );
    }

    @Test
    void testDivisors() {
        var Lib = Library.newInstance();
        Function<Integer, int[]> divisors = N -> {
            var res = Lib.divisors(N);
            Arrays.sort(res);
            return res;
        };
        Assertions.assertAll(
                () -> assertArrayEquals(new int[]{1, 3, 5, 15}, divisors.apply(-15), "negative numbers divisors"),
                () -> assertArrayEquals(new int[0], divisors.apply(0), "0 has no divisor"),
                () -> assertArrayEquals(new int[0], divisors.apply(1), "1 has no divisor"),
                () -> assertArrayEquals(new int[]{1, 2}, divisors.apply(2), "2 has divisors")
        );
    }
}