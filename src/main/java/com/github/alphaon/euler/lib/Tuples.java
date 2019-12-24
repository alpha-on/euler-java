package com.github.alphaon.euler.lib;

public class Tuples {
    private Tuples() {
    }

    public static <A, B> Tuple2<A, B> t2(A a, B b) {
        return new Tuple2<>(a, b);
    }
    public static <A, B, C> Tuple3<A, B, C> t3(A a, B b, C c) {
        return new Tuple3<>(a, b, c);
    }

}
