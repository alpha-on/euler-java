package com.github.alphaon.euler.problems;


/**
 * see: <a href="https://projecteuler.net/problem=6">Sum square difference</a>
 */
public class P006 {
    public String run() {
        // see : https://en.wikipedia.org/wiki/Faulhaber%27s_formula
        var n = 100;
        var sum = n * (n + 1) / 2;
        var sumSquare = n * (n + 1) * (2 * n + 1) / 6;
        int res = sum * sum - sumSquare;
        return String.valueOf(res);

    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P006().run());
    }

}
