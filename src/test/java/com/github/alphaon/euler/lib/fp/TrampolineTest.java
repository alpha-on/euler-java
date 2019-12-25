package com.github.alphaon.euler.lib.fp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static com.github.alphaon.euler.lib.fp.Trampoline.done;

class TrampolineTest {
    @Test
    void testEval() {
        Assertions.assertEquals(0, done(0).eval(), "Done");
        Assertions.assertEquals(0, Trampoline.call(() -> done(0)).eval(), "Call");
    }

    @Test
    void testMap() {
        Function<Integer, Integer> inc = i -> i + 1;
        Assertions.assertEquals(1, done(0).map(inc).eval(), "Done");
        Assertions.assertEquals(1, Trampoline.call(() -> done(0)).map(inc).eval(), "Call");
    }

    @Test
    void testMapCompose() {
        Function<Integer, Integer> inc = i -> i + 1;
        Function<Integer, Integer> mul = i -> i * 2;
        Assertions.assertEquals(2, done(0).map(inc).map(mul).eval(), "Done");
        Assertions.assertEquals(2, Trampoline.call(() -> done(0)).map(inc).map(mul).eval(), "Call");
    }

    @Test
    void testFlatMap() {
        Function<Integer, Trampoline<Integer>> inc = i -> done(i + 1);
        Assertions.assertEquals(1, done(0).flatMap(inc).eval(), "Done(inc)");
        Assertions.assertEquals(1, Trampoline.call(() -> done(0)).flatMap(inc).eval(), "Call(inc)");

        Function<Integer, Trampoline<Integer>> mul = (Integer i) -> Trampoline.call(() -> done(i * 2));
        Assertions.assertEquals(2, done(0).flatMap(inc).flatMap(mul).eval(), "Done(mul ° inc)");
        Assertions.assertEquals(2, Trampoline.call(() -> done(0)).flatMap(inc).flatMap(mul).eval(), "Call(mul ° inc)");

        Function<Integer, Trampoline<String>> asString = (Integer i) -> done("*" + i.toString() + "*");
        Assertions.assertEquals("*2*", done(0).flatMap(inc).flatMap(mul).flatMap(asString).eval(), "Done(mul ° inc)");
        Assertions.assertEquals("*2*", Trampoline.call(() -> done(0)).flatMap(inc).flatMap(mul).flatMap(asString).eval(), "Call(mul ° inc)");
    }
}