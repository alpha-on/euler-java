package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Tuple2;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=5">link</a>
 */
public class P005 {
    public String run() {
        Function<Tuple2<Long, Long>, Long> lcm = t -> t.a * t.b / BigInteger.valueOf(t.a).gcd(BigInteger.valueOf(t.b)).longValue();
        long res = Stream.iterate(t2(1L, 1L), t -> t.b <= 20, t -> t2(lcm.apply(t), t.b + 1))
                .mapToLong(Tuple2::a)
                .max()
                .orElseThrow();
        return String.valueOf(res);

    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P005().run());
    }

}
