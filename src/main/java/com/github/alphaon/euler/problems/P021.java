package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;
import com.github.alphaon.euler.lib.Tuple2;

import java.util.stream.IntStream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=21">Amicable numbers</a>
 */
public class P021 {

    private int sumProperDivisors(int n) {
        return IntStream.rangeClosed(1, n / 2).filter(i -> n % i == 0).sum();
    }

    public String run() {
        var limit = 10_000;
        var res = Streams.range(1, limit)
                .map(i -> t2(i, sumProperDivisors(i)))
                .filter(t -> t.b > 1 && t.b <= limit && !t.a.equals(t.b))
                .filter(t -> sumProperDivisors(t.b) == t.a)
               // .peek(System.out::println)
                .mapToInt(Tuple2::a)
                .sum();


        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P021().run());
    }


}
