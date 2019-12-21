package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * see: <a href="https://projecteuler.net/problem=7">link</a>
 */
public class P007 {

    public String run() {
        Library Lib = new Library();
        var  res = Lib.primeNumbers().limit(10_001).max(Comparator.comparingInt(x -> x)).orElseThrow();

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
