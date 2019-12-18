package com.github.alphaon.euler.lib.fp;

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

    public final T eval() {
        Bounce<?> current = this;
        while (!(current instanceof Done)) {
            if (current instanceof Call) {
                current = ((Call<?>) current).collapse();
            } else if (current instanceof Bounce.FlatMap<?, ?>) {
                current = ((FlatMap<?, ?>) current).collapse();
            } else {
                throw new IllegalStateException("Unexpected Bounce type: " + current.getClass().getSimpleName());
            }
        }
        return ((Done<T>) current).v;
    }

    public final <R> Bounce<R> map(Function<T, R> f) {
        return flatMap(x -> done(f.apply(x)));
    }

    public final <R> Bounce<R> flatMap(Function<T, Bounce<R>> f) {
        return new FlatMap<>(this, f);
    }

    private static final class Done<T> extends Bounce<T> {
        private final T v;

        private Done(T v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "Done{v=" + v + '}';
        }
    }

    private static final class Call<T> extends Bounce<T> {
        private final Supplier<Bounce<T>> thunk;

        private Call(Supplier<Bounce<T>> thunk) {
            this.thunk = thunk;
        }

        private Bounce<T> collapse() {
            return thunk.get();
        }
    }

    private static final class FlatMap<T, R> extends Bounce<R> {
        private final Bounce<T> src;
        private final Function<T, Bounce<R>> transform;

        private FlatMap(Bounce<T> src, Function<T, Bounce<R>> transform) {
            this.src = src;
            this.transform = transform;
        }

        private Bounce<R> collapse() {
            if (src instanceof Done) {
                return transform.apply(((Done<T>) src).v);
            } else if (src instanceof Call) {
                return new FlatMap<>(((Call<T>) src).thunk.get(), transform);
            } else if (src instanceof Bounce.FlatMap) {
                FlatMap<Object, T> fmSrc = (FlatMap<Object, T>) src;
                return new FlatMap<>(
                        fmSrc.src,
                        (x) -> new FlatMap<>(fmSrc.transform.apply(x), transform)
                );
            } else {
                throw new IllegalStateException("Unexpected Bounce type: " + src.getClass().getSimpleName());
            }
        }
    }
}
