package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;

import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;

/**
 * see: <a href="https://projecteuler.net/problem=25">1000-digit Fibonacci number</a>
 */
public class P025 {
    private long findIndex(int size) {
        var min = TEN.pow(size - 1);
        return Stream.iterate(t2(ONE, ONE), t -> t.a.compareTo(min) < 0, t -> t2(t.b, t.a.add(t.b)))
//                .peek(System.out::println)
                .count() + 1;
    }

    public String run() {
        var res = findIndex(1000);
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var p = new P025();
        System.out.println("RES=" + p.run());
    }


}
