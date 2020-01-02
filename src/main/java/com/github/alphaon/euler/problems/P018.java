package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;

import java.util.Arrays;

import static com.github.alphaon.euler.lib.Streams.linesWithoutBlank;
import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=18">Maximum path sum I</a>
 */
public class P018 {

    private int computeValues() {
        int[][] numbers = loadNumbers();
        Streams.rangeClosed(numbers.length - 2, 0)
                .flatMap(row -> Streams.range(0, row + 1).map(col -> t2(row, col)))
                .forEach(t -> numbers[t.a][t.b] += Math.max(numbers[t.a + 1][t.b], numbers[t.a + 1][t.b + 1]));
        return numbers[0][0];
    }

    private int[][] loadNumbers() {
        try (var s = linesWithoutBlank("/data/P018")) {
            return s.map(l -> Arrays.stream(l.split("\\s+")).mapToInt(Integer::valueOf).toArray()).toArray(int[][]::new);
        }
    }

    public String run() {
        var res = computeValues();
        return String.valueOf(res);
    }


    public static void main(String[] args) {
        System.out.println("RES=" + new P018().run());
    }


}
