package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Tuple2;
import com.github.alphaon.euler.lib.Tuple3;

import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static com.github.alphaon.euler.lib.Tuples.t3;
import static java.math.BigInteger.*;
import static java.util.stream.Stream.iterate;

/**
 * see: <a href="https://projecteuler.net/problem=12">link</a>
 */
public class P012 {

    private long triangle(long n) {
        return n * (n + 1) / 2;
    }

    private Tuple2<Integer, Long> countFactors(long divisor, long N) {
        final var ret = iterate(t2(0, N), t -> t2(t.a + 1, t.b / divisor))
                .filter(t -> t.b % divisor != 0)
                .findFirst()
                .orElseThrow();
        return ret;
    }

    private Tuple3<Long, Long, Integer> nextIter(long divisor, long N, int currentNbFactors) {
        var factors = countFactors(divisor, N);
        var nextN = factors.b;
        var isDivisorN = factors.a > 0;
        var nextDiv =  divisor == 2 ? 3 : divisor + 2;
        var nextNbFactors = isDivisorN? currentNbFactors * (factors.a + 1) : currentNbFactors;
        return t3(nextDiv, nextN, nextNbFactors);
    }

    private int countDivisors(Long N) {
        if (1 == N) return 1;
        else if (N == 2) return 2;
        else if (N == 3) return 2;

        long sqrtN = (long) Math.sqrt(N);

        Predicate<Tuple3<Long, Long, Integer>> notHasNest = t -> t.a > sqrtN || t.b <= 1;
        var ret = iterate(t3(2L, N, 1), t -> nextIter(t.a, t.b, t.c))
                .filter(notHasNest)
                .findFirst().orElseGet(() -> t3(2L, N, 1));
        return ret.c + 2;
    }

    public String run() {

        var res = Stream.iterate(1, n -> n + 1)
                .map(n -> t2(n, triangle(n)))
                .map(t -> t3(t.a, t.b, countDivisors(t.b)))
                .peek(System.out::println)
                .filter(t -> t.c > 500)
                .findFirst().orElseThrow();


        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P012().run());
    }

}
