package com.github.alphaon.euler.lib;

import java.util.Objects;
import java.util.function.Function;

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

    public <R> R let(Function<Tuple2<A, B>, R> f) {
        return f.apply(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(a, tuple2.a) &&
                Objects.equals(b, tuple2.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Tuple2{a=" + a + ", b=" + b + '}';
    }
}
