package com.github.alphaon.euler.lib;

import java.util.Objects;

public class Tuple3<A, B, C> extends Tuple2<A, B> {
    public final C c;

    public Tuple3(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public C c() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return Objects.equals(c, tuple3.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), c);
    }

    @Override
    public String toString() {
        return "Tuple3{a=" + a + ", b=" + b + ", c=" + c + '}';
    }
}
