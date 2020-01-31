package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.tools.StopWatch;

import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=30">Digit fifth powers</a>
 */
public class P030 {

    // sum of digit of a power p-th
    private int sumDigitPowerP(int a) {
        return IntStream.iterate(Math.abs(a), n -> n != 0, n -> n / 10).map(n -> (int) Math.pow(n % 10, 5)).sum();
    }

    // if a contains only 9s (ex.:9999) and sumDigitPowerP(a) >= a then it may exist more numbers.
    private boolean existsMoreNumbers(int a) {
        return IntStream.iterate(a, n -> n != 0, n -> n / 10).anyMatch(n -> n % 10 != 9) || sumDigitPowerP(a) >= a;
    }

    private int sumNumbers() {
        return IntStream.iterate(2, this::existsMoreNumbers, n -> n + 1).filter(a -> sumDigitPowerP(a) == a).sum();
    }

    public String run() {
        var res = sumNumbers();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(__ -> {
            var task = new P030();
            var res = new StopWatch().exec(task::run);
            System.out.println("RES=" + res);
        });

    }

}
