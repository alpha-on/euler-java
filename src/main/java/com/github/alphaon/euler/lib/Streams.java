package com.github.alphaon.euler.lib;

import java.io.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.alphaon.euler.lib.Tuples.t2;

public final class Streams {
    private Streams() {
    }

    public static Stream<Integer> range(int startInclusive, int endExclusive) {
        return IntStream.range(startInclusive, endExclusive).boxed();
    }

    public static Stream<Integer> rangeClosed(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive).boxed();
    }

    public static Stream<String> linesWithoutBlank(InputStream in) {
        final var r = new InputStreamReader(in);
        return new BufferedReader(r).lines().map(String::trim).filter(s -> !s.isBlank()).onClose(() -> {
            try {
                in.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    public static <A, B> Stream<Tuple2<A, B>> product(Stream<A> sA, Function<A, Stream<B>> fsb) {
        return sA.flatMap(a -> fsb.apply(a).map(b -> t2(a, b)));
    }
}
