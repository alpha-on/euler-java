package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Library;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

/**
 * see: <a href="https://projecteuler.net/problem=35">Circular primes</a>
 */
public class P035 {

    private final Library lib = Library.newInstance();

    private int[] generateNumbers(int size) {
        int[] ret = new int[(int) Math.pow(5, size)];
        for (int i = 0; i < size; i++) {
            ret[0] = 10 * ret[0] + 1;
        }
        for (int i = 1; i < ret.length; i++) {
            int n = ret[i - 1];
            int q = 1;
            int r = 0;
            for (int j = 0; j < size && n % 10 == 9; j++) {
                r = 10 * r + 1;
                n /= 10;
                q *= 10;
            }
            ret[i] = (n + 2) * q + r;
        }
        return ret;
    }

    private int[] permutations(int N) {
        int size = 1;
        int q = 1;
        for (int nn = N; nn >= 10; nn /= 10) {
            size++;
            q *= 10;
        }
        int[] ret = new int[size];
        ret[0] = N;
        boolean foundCycle = false;
        for (int i = 1; i < size && !foundCycle; i++) {
            int v = 10 * (ret[i - 1] % q) + ret[i - 1] / q;
            foundCycle = v == N;
            if (!foundCycle) {
                ret[i] = v;
            }
        }
        return ret;
    }

    private boolean isCircularPrime(int N) {
        int[] permutations = permutations(N);
        boolean ret = true;
        for (int i = 0; i < permutations.length && permutations[i] != 0 && ret; i++) {
            ret = lib.isPrimeNumber(permutations[i]);
        }
        return ret;
    }

    private int nbCircularPrimesWithSize(int size) {
        int[] numbers = generateNumbers(size);
        int count = 0;
        for (int N : numbers) {
            if (isCircularPrime(N)) {
                count++;
            }
        }
        return count;
    }

    private int nbCircularPrimesWithMaxSize(int maxSize) {
        if (maxSize <= 0) return 0;
        int count = 4; //2, 3, 5, 7
        for (int size = 2; size <= maxSize; size++) {
            count += nbCircularPrimesWithSize(size);
        }
        return count;
    }


    public String run() {
        return nbCircularPrimesWithMaxSize(6) + "";
    }

    public static void main(String[] args) {
        System.out.println(new P035().run());

    }
}
