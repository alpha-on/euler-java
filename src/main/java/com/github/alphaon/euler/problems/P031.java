package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.tools.StopWatch;

import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=31">Coin sums</a>
 */
public class P031 {
    private final int[] pences = new int[]{1, 2, 5, 10, 20, 50, 100, 200};
    private final int amount = 200;

    private static class Node {
        int v;
        int offset;

        public Node(int v, int offset) {
            this.v = v;
            this.offset = offset;
        }

        @Override
        public String toString() {
            return "Node{v=" + v + ", offset=" + offset + '}';
        }
    }

    private long nbCompo() {
        var Q = new Node[amount * pences.length];
        Q[0] = new Node(amount, 0);
        var count = 0;
        var head = 0;
        while (head >= 0) {
            var n = Q[head--];
            if (n.v > 0) {
                for (int i = pences.length - 1; i >= n.offset; i--) {
                    if (n.v - pences[i] >= 0) {
                        Q[++head] = new Node(n.v - pences[i], i);
                    }
                }
            } else if (n.v == 0) {
                count++;
            }
        }
        return count;
    }


    public String run() {
        var res = nbCompo();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int N = 10;
        IntStream.range(0, N).forEach(__ -> {
            var task = new P031();

            var res = new StopWatch().exec(task::run);
            System.out.println("RES=" + res);
        });

    }

}
