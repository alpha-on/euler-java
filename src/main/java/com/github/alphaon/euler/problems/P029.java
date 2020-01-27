package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Tuple2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=29">Distinct powers</a>
 */
public class P029 {

    // find smallest a where exists b such that n = a^b and b >= 2
    private Tuple2<Integer, Integer> findDecompo(int n) {
        var logN = Math.log(n);
        var sqrt = Math.sqrt(n);
        return Stream.iterate(2, i -> i <= sqrt, i -> i + 1)
                .map(a -> t2(a, logN / Math.log(a)))
                .filter(t -> t.b.intValue() == t.b)
                .map(t -> t2(t.a, t.b.intValue()))
                .findFirst()
                .orElse(t2(n, 1));
    }

    private boolean existsFactor(int v, int k, int P) {
        return IntStream.range(1, k).anyMatch(i -> v % i == 0 && v / i <= P);
    }

    private int countDivisors(int n, int P) {
        var d = findDecompo(n);
        return (int) IntStream.rangeClosed(2, P).map(k -> d.b * k).filter(v -> !existsFactor(v, d.b, P)).count();
    }

    private int countDistinctDivisors(int N, int P) {
        return IntStream.rangeClosed(2, N).map(n -> countDivisors(n, P)).sum();
    }

    public String run() {
        var res = countDistinctDivisors(100, 100);
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var res = new P029().run();
        System.out.println("RES=" + res);
    }

}
