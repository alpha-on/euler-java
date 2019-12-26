package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;
import com.github.alphaon.euler.lib.Tuple2;

import java.util.Comparator;
import java.util.stream.LongStream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=14">Longest Collatz sequence</a>
 */
public class P014 {

    private long length(long n) {
        return LongStream.iterate(n, v -> v > 1, v -> (v % 2 == 0) ? v / 2 : 3 * v + 1).count() + 1;
    }

    public String run() {

        var res = Streams.range(1, 1_000_000)
                .map(p -> t2(p, length(p)))
                .max(Comparator.comparingLong(Tuple2::b))
                .orElseThrow();
        return String.valueOf(res.a);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P014().run());
    }


}
