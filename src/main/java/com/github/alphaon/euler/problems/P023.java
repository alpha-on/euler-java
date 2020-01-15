package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=23">Non-abundant sums</a>
 */
public class P023 {
    private Library Lib = Library.newInstance();

    private int properDivisorsSum(int n) {
        return IntStream.of(Lib.divisors(n)).filter(i -> i != n).sum();
    }

    private boolean isAbundantNumber(int n, int[] abundantNumbers) {
        return Arrays.binarySearch(abundantNumbers, n) >= 0;
    }

    private boolean isSumOfTwoAbundanNumbers(int n, int[] abundantNumbers) {
        return IntStream.of(abundantNumbers).takeWhile(v -> v < n).anyMatch(v -> isAbundantNumber(n - v, abundantNumbers));
    }

    public String run() {

        int limit = 28123;
        var abundantNumbers = IntStream.iterate(1, i -> i <= limit, i -> i + 1).filter(n -> properDivisorsSum(n) > n).toArray();
        var res = IntStream.rangeClosed(1, limit)
                .filter(n -> !isSumOfTwoAbundanNumbers(n, abundantNumbers))
                .sum();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var p023 = new P023();
        System.out.println("RES=" + p023.run());
    }


}
