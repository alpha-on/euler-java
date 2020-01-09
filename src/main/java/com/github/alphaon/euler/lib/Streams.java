package com.github.alphaon.euler.lib;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

public final class Streams {
    private Streams() {
    }

    public static Stream<Integer> range(int startInclusive, int endExclusive) {
        return rangeClosed(startInclusive, (startInclusive < endExclusive) ? endExclusive - 1 : endExclusive + 1);
    }

    public static Stream<Integer> rangeClosed(int startInclusive, int endInclusive) {
        var isInc = startInclusive <= endInclusive;
        IntUnaryOperator next = i -> isInc ? i + 1 : i - 1;
        IntPredicate hasNext = i -> isInc ? i <= endInclusive : i >= endInclusive;
        return IntStream.iterate(startInclusive, hasNext, next).boxed();
    }

    public static Stream<String> linesWithoutBlank(InputStream in) {
        return lines(in).map(String::trim).filter(s -> !s.isBlank());
    }

    public static Stream<String> lines(InputStream in) {
        final var r = new InputStreamReader(in);
        return new BufferedReader(r).lines().onClose(() -> {
            try {
                in.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    public static Stream<String> linesWithoutBlank(String classpathResource) {
        return linesWithoutBlank(Streams.class.getResourceAsStream(classpathResource));
    }

    public static <T> Stream<Tuple2<Integer, T>> zipWithIndices(Stream<T> src) {
        var indices = new AtomicInteger(0);
        return src.map(v -> t2(indices.incrementAndGet(), v));
    }

    public static <A, B> Stream<Tuple2<A, B>> product(Stream<A> sA, Function<A, Stream<B>> fsb) {
        return sA.flatMap(a -> fsb.apply(a).map(b -> t2(a, b)));
    }
}
