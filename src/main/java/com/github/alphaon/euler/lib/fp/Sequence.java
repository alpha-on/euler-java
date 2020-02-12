package com.github.alphaon.euler.lib.fp;

import com.github.alphaon.euler.lib.Tuple2;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static java.util.Optional.ofNullable;

public final class Sequence<A> {
    private final Iterator<A> iter;

    private Sequence(Iterator<A> iter) {
        this.iter = iter;
    }

    public <B> Sequence<B> map(Function<A, B> ff) {
        return new Sequence<>(new FlatMapped<>(x -> Sequence.from(ff.apply(x))));
    }

    public <B> Sequence<B> flatMap(Function<A, Sequence<B>> ff) {
        return new Sequence<>(new FlatMapped<>(ff));
    }

    public Sequence<A> filter(Predicate<A> filter) {
        return new Sequence<>(new Filtered(filter));
    }

    public <T> T reduce(T init, BiFunction<T, A, T> merger) {
        var res = init;
        while (iter.hasNext()) res = merger.apply(res, iter.next());
        return res;
    }

    public Optional<A> reduce(BiFunction<A, A, A> merger) {
        if (!iter.hasNext()) return Optional.empty();
        return reduce(ofNullable(iter.next()), (opt, v) -> opt.map(u -> merger.apply(u, v)));
    }

    public Sequence<A> limit(long size) {
        var counter = new AtomicLong(0);
        return takeWhile(__ -> counter.getAndIncrement() < size);
    }

    public Sequence<A> takeWhile(Predicate<A> limiter) {
        return new Sequence<>(new Looped(limiter));
    }

    public Sequence<A> takeUntil(Predicate<A> limiter) {
        return takeWhile(limiter.negate());
    }

    public Optional<A> first(Predicate<A> filter) {
        return filter(filter).limit(1).reduce((u, v) -> u);
    }

    public Sequence<A> unique() {
        var seen = new HashSet<A>();
        return filter(seen::add);
    }

    public List<A> toList() {
        var ret = new ArrayList<A>();
        iter.forEachRemaining(ret::add);
        return ret;
    }

    public long count() {
        var count = 0;
        for (; iter.hasNext(); iter.next(), count++) ;
        return count;
    }

    public Sequence<A> peek(Consumer<A> consumer) {
        return map(v -> {
            consumer.accept(v);
            return v;
        });
    }

    public static <A> Sequence<A> from(Iterator<A> iter) {
        return new Sequence<>(iter);
    }

    public static <A> Sequence<A> from(Iterable<A> iter) {
        return new Sequence<>(iter.iterator());
    }

    @SafeVarargs
    public static <A> Sequence<A> from(A... values) {
        return from(new ArrayIterator<>(values));
    }

    public static <A> Sequence<A> iterate(A seed, Function<A, A> iteration) {
        return new Sequence<>(new Iterated<>(seed, iteration));
    }

    public static <A> Sequence<A> iterate(A seed, Function<A, A> iteration, Predicate<A> limiter) {
        return new Sequence<>(new Iterated<>(seed, iteration)).takeWhile(limiter);
    }

    private static class ArrayIterator<A> implements Iterator<A> {
        private final A[] values;
        private int curIndex = 0;

        private ArrayIterator(A[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return curIndex < values.length;
        }

        @Override
        public A next() {
            return values[curIndex++];
        }
    }

    private static final class Iterated<T> implements Iterator<T> {
        private final Function<T, T> iteration;
        private T seed;

        private Iterated(T seed, Function<T, T> iteration) {
            this.iteration = iteration;
            this.seed = seed;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            T ret = seed;
            seed = iteration.apply(seed);
            return ret;
        }
    }

    private static abstract class BaseIterator<T> implements Iterator<T> {
        private boolean filled;
        private boolean eos;
        private T currentValue;

        protected abstract Tuple2<Boolean, T> tryAdvance();

        @Override
        public final boolean hasNext() {
            if (eos) return false;
            if (filled) return true;

            var next = tryAdvance();
            currentValue = next.b;
            eos = !(filled = next.a);
            return !eos;
        }

        @Override
        public final T next() {
            if (!hasNext()) throw new IllegalStateException("no more item!");
            filled = false;
            return currentValue;
        }
    }


    private final class Filtered extends BaseIterator<A> {
        private final Predicate<A> filter;

        private Filtered(Predicate<A> filter) {
            this.filter = filter;
        }

        protected Tuple2<Boolean, A> tryAdvance() {
            A v = null;
            var hasNext = false;
            while ((hasNext = iter.hasNext()) && (!filter.test(v = iter.next()))) ;
            return t2(hasNext, v);
        }
    }

    private final class Looped extends BaseIterator<A> {
        private final Predicate<A> predicate;

        private Looped(Predicate<A> predicate) {
            this.predicate = predicate;
        }

        protected Tuple2<Boolean, A> tryAdvance() {
            A v = null;
            boolean hasNext = iter.hasNext() && predicate.test(v = iter.next());
            return t2(hasNext, v);
        }
    }

    private final class FlatMapped<B> extends BaseIterator<B> {
        private final Function<A, Sequence<B>> map;
        private Iterator<B> currIter;

        private FlatMapped(Function<A, Sequence<B>> map) {
            this.map = map;
        }


        protected Tuple2<Boolean, B> tryAdvance() {
            if (currIter != null && currIter.hasNext()) return t2(true, currIter.next());
            if (!iter.hasNext()) return t2(false, null);

            currIter = map.apply(iter.next()).iter;
            return t2(currIter.hasNext(), currIter.next());
        }

    }

}
