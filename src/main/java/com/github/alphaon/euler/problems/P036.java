package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=36">Double-base palindromes</a>
 */
public class P036 {

    private final Library lib = Library.newInstance();

    private int sumPalindromesUntil(int N) {
        return IntStream.iterate(1, i -> i <= N, i -> i + 2)
                .filter(lib::isPalyndrom)
                .filter(this::isBinaryPalyndrome)
                .sum();
    }

    private boolean isBinaryPalyndrome(int v) {
        var N = (int) (Math.log(v) / Math.log(2));
        IntFunction<Integer> D = offset -> (v & (1 << offset)) >> offset;
        return IntStream.rangeClosed(0, N / 2).allMatch(i -> D.apply(N - i).equals(D.apply(i)));
    }


    public String run() {
        int res = sumPalindromesUntil(1000000);
        return res + "";
    }

    public static void main(String[] args) {
        System.out.println(new P036().run());
    }
}
