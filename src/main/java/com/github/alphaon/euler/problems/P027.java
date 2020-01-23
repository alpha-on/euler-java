package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;
import com.github.alphaon.euler.lib.Tuple3;
import com.github.alphaon.euler.tools.StopWatch;

import java.util.Comparator;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Streams.rangeClosed;
import static com.github.alphaon.euler.lib.Tuples.t2;
import static com.github.alphaon.euler.lib.Tuples.t3;

/**
 * see: <a href="https://projecteuler.net/problem=27">Quadratic primes</a>
 */
public class P027 {

    private Library Lib = Library.newInstance();

    private int maxCoeffs() {
        return rangeClosed(-999, 999)
                .flatMap(a -> rangeClosed(-1000, 1000).map(b -> t2(a, b)))
                .map(t -> t3(t.a, t.b, countConsecutivePrimes(t.a, t.b)))
                .max(Comparator.comparingLong(Tuple3::c))
                .map(t -> t.a * t.b)
                .orElseThrow();
    }

    private long countConsecutivePrimes(int a, int b) {
        int count = 0;
        for (int n = 0; ; n++) {
            var v = n * n + a * n + b;
            if (v > 1) {
                if (Lib.isPrimeNumber(v)) count++;
                else return count;
            }
        }
    }

    public String run() {
        var res = maxCoeffs();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Stream.iterate(0, i -> i < 10, i -> i + 1).forEach(__ -> {
            var res = new StopWatch().exec(new P027()::run);
            System.out.println("RES=" + res);
        });
    }


}
