package com.github.alphaon.euler.problems;


import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=20">Factorial digit sum</a>
 */
public class P020 {
    public String run() {
        var res = Stream.iterate(t2(1, BigInteger.ONE), t -> t2(t.a + 1, BigInteger.valueOf(t.a + 1).multiply(t.b)))
                .filter(t -> t.a == 100)
                .findFirst()
                .orElseThrow()
                .let(t -> t.b.toString().chars())
                .map(i -> i - '0')
                .sum();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P020().run());
    }


}
