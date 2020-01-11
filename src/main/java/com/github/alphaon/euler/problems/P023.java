package com.github.alphaon.euler.problems;


import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=23">Non-abundant sums</a>
 */
public class P023 {

    private int properDivisorsSum(int n) {
        return IntStream.iterate(1, i -> i < n, i -> i + 1).filter(i -> n % i == 0).sum();
    }

    private boolean isAbundantNumber(int n, int[] abundantNumbers) {
        return Arrays.binarySearch(abundantNumbers, n) >= 0;
    }

    private boolean isSumOfTwoAbundanNumbers(int n, int[] abundantNumbers) {
        return IntStream.of(abundantNumbers).takeWhile(v -> v < n).anyMatch(v -> isAbundantNumber(n - v, abundantNumbers));
    }

    public String run() {

        int limit = 28123;
//        int limit = 30;
        var abundantNumbers = IntStream.iterate(1, i -> i <= limit, i -> i + 1).filter(n -> properDivisorsSum(n) > n).toArray();
        var res = IntStream.rangeClosed(1, limit)
                .filter(n -> !isSumOfTwoAbundanNumbers(n, abundantNumbers))
//                .peek(System.out::println)
                .sum();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P023().run());
    }


}
