package com.github.alphaon.euler.problems;

import com.github.alphaon.euler.lib.Tuple2;
import com.github.alphaon.euler.lib.Tuples;

import java.util.stream.Stream;

/**
 * see: <a href="https://projecteuler.net/problem=2">Even Fibonacci numbers</a>
 */
public class P002 {
    public String run() {
        var result = Stream.iterate(Tuples.t2(1, 1),
                t -> t.b <= 4_000_000,
                t -> Tuples.t2(t.b, t.a + t.b)
        ).mapToInt(Tuple2::b).filter(b -> b%2 == 0).sum();
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println("RES="+new P002().run());
    }
}
