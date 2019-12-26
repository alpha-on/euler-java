package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=7">10001st prime</a>
 */
public class P007 {

    public String run() {
        Library Lib = Library.newInstance();
        var res = Lib.streamPrimeNumbers().limit(10_001).max().orElseThrow();

//
//        var knownPrimeNumbers = new LinkedList<>(List.of(2));
//        for (int p = 3; knownPrimeNumbers.size() < 10_001; p += 2) {
//            if (Lib.isPrimeNumber(knownPrimeNumbers, p)) knownPrimeNumbers.add(p);
//        }
//
//        int res = knownPrimeNumbers.getLast();

        return String.valueOf(res);

    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P007().run());
    }

}
