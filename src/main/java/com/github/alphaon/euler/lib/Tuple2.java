package com.github.alphaon.euler.lib;

public class Tuple2<A, B> {
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A a() {
        return a;
    }

    public B b() {
        return b;
    }

    @Override
    public String toString() {
        return "Tuple2{a=" + a + ", b=" + b + '}';
    }
}
