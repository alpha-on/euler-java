package com.github.alphaon.euler.problems;


import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static com.github.alphaon.euler.lib.Streams.zipWithIndices;

/**
 * see: <a href="https://projecteuler.net/problem=22">Names scores</a>
 */
public class P022 {
    public String run() {
        try (var is = getClass().getResourceAsStream("/data/P022")) {
            var scanner = new Scanner(is).useDelimiter("\\s*,\\s*");
            var res = zipWithIndices(scanner.findAll(Pattern.compile("\\w+")).map(MatchResult::group).sorted())
                    .mapToInt(t -> t.b.chars().map(i -> i - 'A' + 1).sum() * t.a)
                    .sum();
            return String.valueOf(res);

        } catch (IOException ix) {
            throw new UncheckedIOException(ix);
        }
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P022().run());
    }


}
