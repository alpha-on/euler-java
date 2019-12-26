package com.github.alphaon.euler.problems;

import com.github.alphaon.euler.lib.Tuple2;
import com.github.alphaon.euler.lib.Tuples;

import java.util.stream.Stream;

/**
 * see: <a href="https://projecteuler.net/problem=3">Largest prime factor</a>
 */
public class P003 {

    private Tuple2<Long, Long> nextIter(Tuple2<Long, Long> src) {
        if (src.a % src.b == 0) return Tuples.t2(src.a / src.b, src.b);
        else if (src.b == 2) return Tuples.t2(src.a, 3L);
        else return Tuples.t2(src.a, src.b + 2);
    }

    public String run() {
        long n = 600851475143L;
        long limit = (long) (Math.sqrt(n) + 1);
        var result = Stream.iterate(Tuples.t2(n, 2L),
                t -> t.a >= t.b && t.b <= limit,
                this::nextIter
        ).mapToLong(Tuple2::a).min().orElse(-1);
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P003().run());
    }
}
