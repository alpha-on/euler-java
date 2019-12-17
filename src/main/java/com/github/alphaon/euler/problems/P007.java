package com.github.alphaon.euler.problems;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * see: <a href="https://projecteuler.net/problem=7">link</a>
 */
public class P007 {

    private boolean isPrimeNumber(Collection<Integer> knownPrimeNumbers, Integer v) {
        var limit = Math.sqrt(v);
        return knownPrimeNumbers.stream().takeWhile(it -> it <= limit).noneMatch(it -> v % it == 0);
    }


    public String run() {
        var knownPrimeNumbers = new LinkedList<>(List.of(2));

        for (int p = 3; knownPrimeNumbers.size() < 10_001; p += 2) {
            if (isPrimeNumber(knownPrimeNumbers, p)) knownPrimeNumbers.add(p);
        }


        int res = knownPrimeNumbers.getLast();

        return String.valueOf(res);

    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P007().run());
    }

}
