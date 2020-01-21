package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;
import com.github.alphaon.euler.lib.Streams;
import com.github.alphaon.euler.lib.Tuple2;

import java.util.Comparator;
import java.util.stream.IntStream;

import static com.github.alphaon.euler.lib.Streams.rangeClosed;
import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=27">Quadratic primes</a>
 */
public class P027 {
    private Library Lib = Library.newInstance();

    private Tuple2<Integer, Integer> maxCoeffs() {
        return rangeClosed(-999, 999)
                .flatMap(a -> rangeClosed(-1000, 1000).map(b -> t2(a, b)))
                .max(Comparator.comparingLong(t -> countConsecutivePrimes(t.a, t.b)))
                .orElseThrow();
    }


    private long countConsecutivePrimes(int a, int b) {
        return IntStream.iterate(0, n -> n + 1)
                .map(n -> n * n + a * n + b)
                .filter(v -> v > 0)
                .takeWhile(Lib::isPrimeNumber).count();
    }


    public String run() {
        var res = Streams.range(2, 1_000).max(Comparator.comparingInt(this::findCycleLength)).orElseThrow();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var p = new P027();
        System.out.println("RES=" + p.run());
    }


}
