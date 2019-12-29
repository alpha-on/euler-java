package com.github.alphaon.euler.problems;


import com.github.alphaon.euler.lib.Streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=16">Power digit sum</a>
 */
public class P017 {

    private int wordSizeOfDizens(int N) {
        var units = N % 10;
        return wordSizeOf(units) + wordSizeOf(N - units);
    }

    private int wordSizeOfHundreds(int N) {
        var dizens = N % 100;
        return wordSizeOf(N / 100) + hundred.length() + (dizens != 0 ? and.length() + wordSizeOf(dizens) : 0);
    }

    private int wordSizeOfThousand(int N) {
        return wordSizeOf(N / 1_000) + thousand.length();
    }

    private int wordSizeOf(int N) {
        var s = knownNumberWords.get(N);
        if (s != null) return s.length();
        else if (N == 0) return 0;
        else if (20 <= N && N < 100) return wordSizeOfDizens(N);
        else if (100 <= N && N < 1_000) return wordSizeOfHundreds(N);
        else if (N == 1_000) return wordSizeOfThousand(N);
        else throw new IllegalArgumentException("No word defined for '" + N + "'!");

    }

    public String run() {
        var res = IntStream.rangeClosed(0, 1_000).map(this::wordSizeOf).sum();
        return String.valueOf(res);
    }

    private static final Map<Integer, String> knownNumberWords = new HashMap<>() {{
        try (var stream = Streams.linesWithoutBlank("/data/P017")) {
            stream.map(s -> s.split(":")).forEach(parts -> put(Integer.valueOf(parts[0]), parts[1]));
        }
    }};
    private static final String hundred = "hundred";
    private static final String and = "and";
    private static final String thousand = "thousand";

    public static void main(String[] args) {
        System.out.println("RES=" + new P017().run());
    }
}
