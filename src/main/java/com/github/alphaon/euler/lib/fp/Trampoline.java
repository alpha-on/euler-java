package com.github.alphaon.euler.lib.fp;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Trampoline<T> {
    private Trampoline() {
    }

    public static <T> Trampoline<T> done(T value) {
        return new Done<>(value);
    }

    public static <T> Trampoline<T> call(Supplier<Trampoline<T>> thunk) {
        return new Call<>(thunk);
    }

    public static <T> Trampoline<List<T>> all(List<Trampoline<T>> trampolines) {
        BiFunction<List<T>, List<T>, List<T>> mergeList = (l1, l2) -> new LinkedList<>(l1) {{
            addAll(l2);
        }};
        return trampolines.stream()
                .map(t -> t.map(List::of))
                .reduce(done(List.of()), (t1, t2) -> t1.flatMap(l1 -> t2.map(l2 -> mergeList.apply(l1, l2))));
    }

    public final T eval() {
        Trampoline<?> current = this;
        while (!(current instanceof Done)) {
            if (current instanceof Call) {
                current = ((Call<?>) current).collapse();
            } else if (current instanceof Trampoline.FlatMap<?, ?>) {
                current = ((FlatMap<?, ?>) current).collapse();
            } else {
                throw new IllegalStateException("Unexpected Bounce type: " + current.getClass().getSimpleName());
            }
        }
        return ((Done<T>) current).v;
    }

    public final <R> Trampoline<R> map(Function<T, R> f) {
        return flatMap(x -> done(f.apply(x)));
    }

    public final <R> Trampoline<R> flatMap(Function<T, Trampoline<R>> f) {
        return new FlatMap<>(this, f);
    }

    private static final class Done<T> extends Trampoline<T> {
        private final T v;

        private Done(T v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "Done{v=" + v + '}';
        }
    }

    private static final class Call<T> extends Trampoline<T> {
        private final Supplier<Trampoline<T>> thunk;

        private Call(Supplier<Trampoline<T>> thunk) {
            this.thunk = thunk;
        }

        private Trampoline<T> collapse() {
            return thunk.get();
        }
    }

    private static final class FlatMap<T, R> extends Trampoline<R> {
        private final Trampoline<T> src;
        private final Function<T, Trampoline<R>> transform;

        private FlatMap(Trampoline<T> src, Function<T, Trampoline<R>> transform) {
            this.src = src;
            this.transform = transform;
        }

        private Trampoline<R> collapse() {
            if (src instanceof Done) {
                return transform.apply(((Done<T>) src).v);
            } else if (src instanceof Call) {
                return ((Call<T>) src).thunk.get().flatMap(transform);
            } else if (src instanceof Trampoline.FlatMap) {
                FlatMap<Object, T> fmSrc = (FlatMap<Object, T>) src;
                return new FlatMap<>(
                        fmSrc.src,
                        (x) -> fmSrc.transform.apply(x).flatMap(transform)
                );
            } else {
                throw new IllegalStateException("Unexpected Bounce type: " + src.getClass().getSimpleName());
            }
        }
    }
}
