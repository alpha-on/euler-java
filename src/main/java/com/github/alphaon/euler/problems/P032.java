package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.fp.Sequence;
import com.github.alphaon.euler.tools.StopWatch;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=32">Pandigital products</a>
 */
public class P032 {

    private boolean is9PandigitalProduct(int a, int b, int c) {
        int[] seen = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        boolean res = true;
        int nbDigitOK = 1;
        for (int n : new int[]{a, b, c}) {
            for (; n != 0 && res; n /= 10) {
                res = (++seen[n % 10] <= 1);
                nbDigitOK += res ? 1 : 0;
            }
            if (!res) break;
        }
        res &= (nbDigitOK == 10 || a == 0 || b == 0 || c == 0);
        return res;
    }

    private int nbDigits(int a, int b, int c) {
        var s = 0;
        for (int n : new int[]{a, b, c}) {
            for (; n != 0; n /= 10, s++) ;
        }
        return s;
    }

    private int sumProduct() {
        Set<Integer> numbers = new HashSet<>();
        int i;
        var hasNext = true;
        for (i = 2; hasNext; i++) {
            var hasMore = true;
            hasNext = false;
            for (int j = i + 1; hasMore; j++) {
                int k = i * j;
                hasMore = nbDigits(i, j, k) <= 9;
                hasNext |= hasMore;
                if (is9PandigitalProduct(i, j, k)) numbers.add(k);
            }
        }
        return Sequence.from(numbers).reduce(0, Integer::sum);
        // return numbers.stream().mapToInt(x -> x).sum();

    }


    public String run() {
        var res = sumProduct();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int N = 20;
        IntStream.range(0, N).forEach(__ -> {
            var task = new P032();

            var res = new StopWatch().exec(task::run);
            System.out.println("RES=" + res);
        });

    }

}
