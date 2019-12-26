package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;
import com.github.alphaon.euler.lib.Tuples;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Stream.iterate;

/**
 * see: <a href="https://projecteuler.net/problem=4">Largest palindrome product</a>
 */
public class P004 {
    public String run() {
        var lib = Library.newInstance();
        Supplier<Stream<Integer>> si = () -> iterate(100, i -> i < 1000, i -> i + 1);
        Supplier<Stream<Integer>> sj = () -> iterate(100, j -> j < 1000, j -> j + 1);
        var maxPalyndrom = si.get().flatMap(i -> sj.get().map(j -> Tuples.t2(i, j)))
                .mapToLong(p -> p.a * p.b)
                .filter(lib::isPalyndrom)
                .max().orElseThrow();

        return String.valueOf(maxPalyndrom);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P004().run());
    }
}
