package com.github.alphaon.euler.lib.fp;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Bounce<T> {
    private Bounce() {
    }

    public static <T> Bounce<T> done(T value) {
        return new Done<>(value);
    }

    public static <T> Bounce<T> call(Supplier<Bounce<T>> thunk) {
        return new Call<>(thunk);
    }

    public T eval() {
        var stack = new LinkedList(List.of(this));
        Object lastValue = null;
        while (!stack.isEmpty()) {
            var b = stack.pop();
            if (b instanceof Done) {
                lastValue = ((Done) b).v;
            } else if (b instanceof Call) {
                stack.push(((Call) b).thunk.get());
            } else if (b instanceof FlatMapped) {
                stack.push(((FlatMapped) b).transform);
                stack.push(((FlatMapped) b).src);
            } else if (b instanceof Transform) {
                stack.push(((Transform) b).t.apply(lastValue));
            } else throw new IllegalStateException("Unexpected Bounce type: " + b.getClass().getSimpleName());
        }
        return (T) lastValue;
    }

    public final <R> Bounce<R> map(Function<T, R> f) {
        return flatMap(x -> done(f.apply(x)));
    }

    public final <R> Bounce<R> flatMap(Function<T, Bounce<R>> f) {
        return new FlatMapped<>(this, f);
    }

    private static final class Done<T> extends Bounce<T> {
        private final T v;

        private Done(T v) {
            this.v = v;
        }
    }

    private static final class Call<T> extends Bounce<T> {
        private final Supplier<Bounce<T>> thunk;

        private Call(Supplier<Bounce<T>> thunk) {
            this.thunk = thunk;
        }
    }

    private static final class FlatMapped<T, R> extends Bounce<R> {
        private final Bounce<T> src;
        private final Transform<T, R> transform;

        public FlatMapped(Bounce<T> src, Function<T, Bounce<R>> transform) {
            this.src = src;
            this.transform = new Transform<>(transform);
        }
    }

    private static final class Transform<T, R> extends Bounce<R> {
        private final Function<T, Bounce<R>> t;

        public Transform(Function<T, Bounce<R>> t) {
            this.t = t;
        }
    }
}
