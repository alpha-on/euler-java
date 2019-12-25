package com.github.alphaon.euler.lib.fp;

import java.math.BigInteger;

public class DemoTrampoline {

    private static Trampoline<BigInteger> fact(int n) {
        if (n <= 0) return Trampoline.done(BigInteger.ONE);
        else return Trampoline.call(() -> fact(n - 1)).map(v -> BigInteger.valueOf(n).multiply(v));
    }

    public static void main(String[] args) {
        System.out.println(fact(100_000).eval());
    }

}
