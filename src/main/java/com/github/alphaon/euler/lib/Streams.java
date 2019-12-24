package com.github.alphaon.euler.lib;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Streams {
    private Streams() {
    }

    public static Stream<Integer> range(int startInclusive, int endExclusive) {
        return IntStream.range(startInclusive, endExclusive).boxed();
    }
    public static Stream<Integer> rangeClosed(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive).boxed();
    }
}
