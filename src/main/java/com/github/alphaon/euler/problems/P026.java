package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;

import java.util.Comparator;

/**
 * see: <a href="https://projecteuler.net/problem=26">Reciprocal cycles</a>
 */
public class P026 {
    private int findCycleLength(int divisor) {
        var seen = new boolean[divisor + 1];
        var ranks = new int[divisor + 1];
        seen[1] = true;
        ranks[1] = 1;
        int r = 1;
        int rank = 1;
        while (true) {
            rank++;
            r = (10 * r) % divisor;
            if (r == 0) {
                return 0;
            } else if (seen[r]) {
                return rank - ranks[r];
            } else {
                seen[r] = true;
                ranks[r] = rank;
            }
        }
    }


    public String run() {
        var res = Streams.range(2, 1_000).max(Comparator.comparingInt(this::findCycleLength)).orElseThrow();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var p = new P026();
        System.out.println("RES=" + p.run());
    }


}
