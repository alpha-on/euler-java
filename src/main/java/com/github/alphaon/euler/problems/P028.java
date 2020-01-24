package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.tools.StopWatch;

import java.util.stream.Stream;

/**
 * see: <a href="https://projecteuler.net/problem=28">Number spiral diagonals</a>
 */
public class P028 {


    private int sumDiagonal(int size) {
        int s = 0;
        for (int x = 0, y = 0, dx = 0, dy = 0, v = 1; v <= size * size; v++, x += dx, y += dy) {
            if (x == y) {
                s += v;
                dx = x >= 0 ? 1 : 0;
                dy = x >= 0 ? 0 : 1;
            } else if (x == y + 1 && x > 0) {
                dx = 0;
                dy = -1;
            } else if (x == -y) {
                s += v;
                dx = x >= 0 ? -1 : 1;
                dy = 0;
            }
        }
        return s;
    }


    public String run() {
        var res = sumDiagonal(1001);
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Stream.iterate(0, i -> i < 10, i -> i + 1).forEach(__ -> {
            var res = new StopWatch().exec(new P028()::run);
            System.out.println("RES=" + res);
        });
    }


}
