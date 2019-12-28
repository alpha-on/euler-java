package com.github.alphaon.euler.problems;


import java.math.BigInteger;

/**
 * see: <a href="https://projecteuler.net/problem=16">Power digit sum</a>
 */
public class P016 {
    public String run() {
        var res = BigInteger.ONE.shiftLeft(1_000).toString().chars().map(i -> i - '0').sum();
        return String.valueOf(res);
    }
    public static void main(String[] args) {
        System.out.println("RES=" + new P016().run());
    }
}
