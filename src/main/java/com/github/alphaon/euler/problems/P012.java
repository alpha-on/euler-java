package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Tuple2;
import com.github.alphaon.euler.lib.fp.Trampoline;

import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static com.github.alphaon.euler.lib.Tuples.t3;
import static com.github.alphaon.euler.lib.fp.Trampoline.call;
import static com.github.alphaon.euler.lib.fp.Trampoline.done;
import static java.util.stream.Stream.iterate;

/**
 * see: <a href="https://projecteuler.net/problem=12">link</a>
 */
public class P012 {
    private long triangle(long n) {
        return n * (n + 1) / 2;
    }

    private Trampoline<Integer> countDivisors(long N, int p) {
        if (N == 1) return done(1);

        int nextPM = p == 2 ? 3 : p + 2;

        if (p > Math.sqrt(N)) return done(2); // N is a prime number
        if (N % p != 0) return Trampoline.call(() -> countDivisors(N, nextPM)); // try next prime number

        var fact = factors(N, p);
        return call(() -> countDivisors(fact.b, nextPM)).map(c -> c * (fact.a + 1));
    }

    // returns (nbFactor, N / divisor^nbFactor). Ex: if N = 45, divisor = 3 then returns (2, 5), since 45 = 3^2 * 5
    private Tuple2<Integer, Long> factors(long N, int divisor) {
        return iterate(t2(0, N), t -> t2(t.a + 1, t.b / divisor))
                .filter(t -> t.b % divisor != 0).findFirst()
                .orElseThrow(); // should not happen, since p is a divisor of N
    }

    private Integer countDivisors(long N) {
        final var ret = countDivisors(N, 2).eval();
        return ret;
    }


    public String run() {

        var res = Stream.iterate(1, n -> n + 1)
                .map(n -> t2(n, triangle(n)))
                .map(t -> t3(t.a, t.b, countDivisors(t.b)))
                //  .peek(System.out::println)
                .filter(t -> t.c > 500)
                .findFirst().orElseThrow();


        return String.valueOf(res.b);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P012().run());
    }


}
