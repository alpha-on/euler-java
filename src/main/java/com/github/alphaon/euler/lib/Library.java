package com.github.alphaon.euler.lib;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static java.util.stream.Stream.iterate;

public class Library {
    public boolean isPalyndrom(long number) {
        UnaryOperator<Tuple2<Long, Long>> next = t -> t2(t.a == 0 ? -1 : t.a / 10, (10 * t.b) + (t.a % 10));
        Predicate<Tuple2<Long, Long>> hasNext = t -> t.a >= 0;

        var n = Math.abs(number);
        var mirror = iterate(t2(n, 0L), hasNext, next).mapToLong(Tuple2::b).max().orElseThrow();
        return mirror == n;
    }
}
