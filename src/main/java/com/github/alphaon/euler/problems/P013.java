package com.github.alphaon.euler.problems;


import java.math.BigInteger;

import static com.github.alphaon.euler.lib.Streams.linesWithoutBlank;

/**
 * see: <a href="https://projecteuler.net/problem=13">Large sum</a>
 */
public class P013 {

    public String run() {
        try (var data = linesWithoutBlank("/data/P013")) {
            var res = data.map(BigInteger::new).reduce(BigInteger.ZERO, BigInteger::add);
            return String.format("%10.10s", res);
        }
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P013().run());
    }


}
