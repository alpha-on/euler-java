package com.github.alphaon.euler.problems;


import static com.github.alphaon.euler.lib.Tuples.t2;
import static com.github.alphaon.euler.lib.Tuples.t3;
import static java.util.stream.Stream.iterate;

/**
 * see: <a href="https://projecteuler.net/problem=9">link</a>
 */
public class P009 {

    public String run() {
        int l = 1000;
        var res = iterate(1, a -> a <= l - 2, a -> a + 1)
                .flatMap(a -> iterate(t2(a, a + 1), t -> t.b <= l - 1, T -> t2(a, T.b + 1)))
                .map(t -> t3(t.a, t.b, l - t.a - t.b))
                .filter(t -> t.a * t.a + t.b * t.b == t.c * t.c)
                .findFirst().orElseThrow();


        return String.valueOf(res.a * res.b * res.c);
    }


    public static void main(String[] args) {
        System.out.println("RES=" + new P009().run());
    }

}
