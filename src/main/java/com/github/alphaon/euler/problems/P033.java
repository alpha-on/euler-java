package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.fp.Sequence;
import com.github.alphaon.euler.tools.StopWatch;

import java.math.BigInteger;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=33">Digit cancelling fractions</a>
 */
public class P033 {

    private Sequence<Integer> numbers(int start, int endInclusive) {
        return Sequence.iterate(start, n -> n + 1, n -> n <= endInclusive).filter(n -> n % 10 != 0);
    }

    private int findDenominator() {
        var res = numbers(11, 98).flatMap(a -> numbers(a + 1, 99).map(b -> t2(a, b)))
                .flatMap(t -> Sequence.from(
                        t.a / 10 == t.b / 10 ? t2(t, t2(t.a % 10, t.b % 10)) : null,
                        t.a / 10 == t.b % 10 ? t2(t, t2(t.a % 10, t.b / 10)) : null,
                        t.a % 10 == t.b / 10 ? t2(t, t2(t.a / 10, t.b % 10)) : null,
                        t.a % 10 == t.b % 10 ? t2(t, t2(t.a / 10, t.b / 10)) : null))
                .filter(Objects::nonNull)
                .filter(t -> t.a.a * t.b.b == t.a.b * t.b.a)
                .reduce(t2(1, 1), (v, t) -> t2(v.a * t.a.a, v.b * t.a.b));
        int gcd = BigInteger.valueOf(res.a).gcd(BigInteger.valueOf(res.b)).intValue();

        return res.b / gcd;

    }

    public String run() {
        var res = findDenominator();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int N = 20;
        IntStream.range(0, N).forEach(__ -> {
            var task = new P033();

            var res = new StopWatch().exec(task::run);
            System.out.println("RES=" + res);
        });

    }

}
