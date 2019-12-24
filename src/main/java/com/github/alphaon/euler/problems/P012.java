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

    private BigInteger triangle(BigInteger n) {
        return n.multiply(n.add(ONE)).divide(TWO);
    }

    private Tuple2<Integer, BigInteger> countFactors(BigInteger divisor, BigInteger N) {
        final var ret = iterate(t2(0, N), t -> t2(t.a + 1, t.b.divide(divisor)))
                .filter(t -> !t.b.mod(divisor).equals(ZERO))
                .findFirst()
                .orElseThrow();
        return ret;
    }

    private Tuple3<BigInteger, BigInteger, Integer> nextIter(BigInteger divisor, BigInteger N, Integer currentNbFactors, BigInteger l) {
        var factors = countFactors(divisor, N);
        var nextN = factors.b;
        var isDivisorN = factors.a > 0;
        var nextDiv = nextN.compareTo(l) >= 0 && isDivisorN ? nextN : divisor.equals(TWO) ? BigInteger.valueOf(3) : divisor.add(TWO);
        var nextNbFactors = currentNbFactors + factors.a;
        return t3(nextDiv, nextN, nextNbFactors);
    }

    private int countDivisors(BigInteger N) {
        if (N.equals(ONE)) return 1;
        else if (N.equals(TWO)) return 2;
        else if (N.equals(BigInteger.valueOf(3))) return 2;

        BigInteger sqrtN = N.sqrt();

        Predicate<Tuple3<BigInteger, BigInteger, Integer>> notHasNest = t -> t.b.compareTo(ONE) <= 0;
        var ret = iterate(t3(TWO, N, 0), t -> nextIter(t.a, t.b, t.c, sqrtN))
                .filter(notHasNest)
                .findFirst().orElseGet(() -> t3(TWO, N, 0));
        return ret.c + 2;
    }

    public String run() {

        var res = Stream.iterate(TEN, n -> n.add(ONE))
                .map(n -> t2(n, triangle(n)))
                .map(t -> t3(t.a, t.b, countDivisors(t.b)))
                .peek(System.out::println)
                .filter(t ->  t.c > 20)
                .findFirst().orElseThrow();


        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P012().run());
    }

}
