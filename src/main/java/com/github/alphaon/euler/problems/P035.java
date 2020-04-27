package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t3;

/**
 * see: <a href="https://projecteuler.net/problem=35">Circular primes</a>
 */
public class P035 {

    private final Library lib = Library.newInstance();

    private IntStream generateNumbers(int size) {
        int M = (int) Math.pow(10, size);
        return IntStream.iterate((M - 1) / 9, nn -> nn < M, nn ->
                Stream.iterate(t3(nn, 1, 0), t -> t3(t.a / 10, t.b * 10, 10 * t.c + 1))
                        .dropWhile(t -> t.a % 10 == 9)
                        .findFirst().or(() -> Optional.of(t3(nn, 1, 0)))
                        .map(t -> (t.a + 2) * t.b + t.c)
                        .orElseThrow());
    }

    private IntStream permutations(int N) {
        int q = IntStream.iterate(N, nn -> nn >= 10, nn -> nn / 10).map(__ -> 10).reduce(1, (a, b) -> a * b);
        return IntStream.concat(IntStream.of(N),
                IntStream.iterate(10 * (N % q) + N / q, nn -> nn != N, nn -> 10 * (nn % q) + nn / q)
        );
    }

    private boolean isCircularPrime(int N) {
        return permutations(N).allMatch(lib::isPrimeNumber);
    }

    private int nbCircularPrimesWithSize(int size) {
        return (int) generateNumbers(size).filter(this::isCircularPrime).count();
    }

    private int nbCircularPrimesWithMaxSize(int maxSize) {
        if (maxSize <= 0) return 0;
        return IntStream.rangeClosed(2, maxSize).map(this::nbCircularPrimesWithSize).sum() + 4;
    }


    public String run() {
        return nbCircularPrimesWithMaxSize(6) + "";
    }

    public static void main(String[] args) {
        System.out.println(new P035().run());

    }
}
