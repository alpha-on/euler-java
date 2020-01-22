package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;
import com.github.alphaon.euler.lib.Tuple2;
import com.github.alphaon.euler.tools.StopWatch;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static com.github.alphaon.euler.lib.Streams.rangeClosed;
import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=27">Quadratic primes</a>
 */
public class P027 {
    private Library Lib = Library.newInstance();
    private Map<Integer, Boolean> cachedPrime = new TreeMap<>();
    private int[] primeNumbers = new int[2_000_000];

    private Tuple2<Integer, Integer> maxCoeffs() {
        return rangeClosed(-999, 999)
                .flatMap(a -> rangeClosed(-1000, 1000).map(b -> t2(a, b)))
                .max(Comparator.comparingLong(t -> countConsecutivePrimes(t.a, t.b)))
                .orElseThrow();
    }

    private boolean isPrimeNumber(int N) {
        boolean ret;
        if (N == 2) {
            ret = true;
        } else if (N % 2 == 0) {
            ret = false;
        } else if (N > primeNumbers.length) {
            ret = Lib.isPrimeNumber(N);
        } else if (primeNumbers[N] == 1) {
            ret = true;
        } else if (primeNumbers[N] == -1) {
            ret = false;
        } else {
//            ret = BigInteger.valueOf(N).isProbablePrime(10);
            ret = Lib.isPrimeNumber(N);
            if (ret) primeNumbers[N] = 1;
            else primeNumbers[N] = -1;
        }
        return ret;
    }


    private long countConsecutivePrimes(int a, int b) {
        return IntStream.iterate(0, n -> n + 1)
                .map(n -> n * n + a * n + b)
                .filter(v -> v > 1)
                .takeWhile(this::isPrimeNumber)
//                .peek(System.out::println)
                .count();
    }


    public String run() {
        var res = maxCoeffs();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        // RES=Tuple2{a=-61, b=971}, in 70598 :: Lib.isPrimeNumber
        // RES=Tuple2{a=-61, b=971}, in 12579 :: this.isPrimeNumber
        var p = new P027();
        var res = new StopWatch().exec(p::run);
        System.out.println("RES=" + res);
    }


}
