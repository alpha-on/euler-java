package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.Comparator;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static com.github.alphaon.euler.lib.Tuples.t3;
import static java.util.stream.Stream.iterate;

/**
 * see: <a href="https://projecteuler.net/problem=10">link</a>
 */
public class P010 {

    public String run() {
        Library Lib = Library.newInstance();
        var  res = Lib.streamPrimeNumbers().takeWhile( p -> p < 2_000_000).mapToLong(x -> x).sum();

        return String.valueOf(res);
    }


    public static void main(String[] args) {
        System.out.println("RES=" + new P010().run());
    }

}
