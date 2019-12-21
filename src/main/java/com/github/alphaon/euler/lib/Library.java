package com.github.alphaon.euler.lib;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

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

    public boolean isPrimeNumber(Collection<Integer> knownPrimeNumbers, Integer v) {
        var limit = Math.sqrt(v);
        return knownPrimeNumbers.stream().takeWhile(it -> it <= limit).noneMatch(it -> v % it == 0);
    }

    public Stream<Integer> primeNumbers() {
        return Stream.generate(new Supplier<>() {
            int count = 0;
            LinkedList<Integer> knownPrimeNumbers = new LinkedList<>(List.of(2, 3));

            @Override
            public Integer get() {
                count++;
                if (count == 1) return 2;
                else if (count == 2) return 3;
                else {
                    int nextPrime = knownPrimeNumbers.getLast() + 2;
                    for (; !isPrimeNumber(knownPrimeNumbers, nextPrime); nextPrime += 2);
                    knownPrimeNumbers.addLast(nextPrime);
                    return nextPrime;
                }
            }
        });
    }
}
