package com.github.alphaon.euler.problems;


import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=34">Digit factorials</a>
 */
public class P034 {

    private int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};


    private boolean same(int a, int b) {
        int[] da = new int[10];
        int[] db = new int[10];
        int qa, qb;
        for (qa = a, qb = b; qa != 0 && qb != 0; qa /= 10, qb /= 10) {
            da[qa % 10]++;
            db[qb % 10]++;
        }
        if (qa != qb) return false;
        for (int i = 0; i < 10; i++) if (da[i] != db[i]) return false;
        return true;
    }

    private int next(final int n) {
        return Stream.iterate(t2(n, 1), t -> t2(t.a / 10, t.b * 10))
                .filter(t -> t.a % 10 != (t.a / 10) % 10)
                .findFirst()
                .map(t -> (t.a + 1) * t.b)
                .orElseThrow();

    }

    private boolean hasNext(final int n) {
        int s = 0;
        for (int a = n; a != 0; a /= 10) s += factorials[9];
        return s >= n;
    }

    private int sum(final int n) {
        return IntStream.iterate(n, a -> a != 0, a -> a / 10).map(v -> factorials[v % 10]).sum();
    }


    public String run() {
        final var sum = Stream.iterate(3, this::hasNext, this::next)
                .map(a -> t2(a, sum(a)))
                .filter(t -> same(t.a, t.b))
                .mapToInt(t -> t.b).sum();
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        System.out.println(new P034().run());

    }
}
