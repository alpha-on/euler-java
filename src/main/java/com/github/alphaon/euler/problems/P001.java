package com.github.alphaon.euler.problems;

import java.util.stream.Stream;

/**
 * See: <a href="https://projecteuler.net/problem=1">Multiples of 3 and 5</a>
 */
public class P001 {
    String run() {
        int result = Stream.iterate(1, i -> i < 1000, i -> i + 1)
                .mapToInt(x -> x)
                .filter(num -> num % 3 == 0 || num % 5 == 0)
                .sum();
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(new P001().run());
    }
}
