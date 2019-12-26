package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;
import com.github.alphaon.euler.lib.Tuple2;

import java.util.function.Function;

import static com.github.alphaon.euler.lib.Streams.range;
import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=15">Lattice paths</a>
 */
public class P015 {

    private static final int GRID_SIZE = 20;

    public String run() {

        int n = GRID_SIZE + 1;
        long[][] grid = new long[n][n];
        grid[0][0] = 1;
        Function<Tuple2<Integer, Integer>, Long> cellAt = t -> t.a >= 0 && t.b >= 0 ? grid[t.a][t.b] : 0;
        Streams.product(range(0, n), b -> range(0, n))
                .filter(t -> t.a != 0 || t.b != 0)
                .forEach(t -> grid[t.a][t.b] = cellAt.apply(t2(t.a - 1, t.b)) + cellAt.apply(t2(t.a, t.b - 1)));

        var res = grid[GRID_SIZE][GRID_SIZE];
        return String.valueOf(res);
    }


    public static void main(String[] args) {
        System.out.println("RES=" + new P015().run());
    }


}
