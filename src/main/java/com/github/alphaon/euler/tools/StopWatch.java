package com.github.alphaon.euler.tools;

import java.util.function.Supplier;

public class StopWatch {

    public <T> EllapsedTime<T> exec(Supplier<T> task) {
        var start = System.nanoTime();
        var result = task.get();
        var end = System.nanoTime();
        return new EllapsedTime<>((end - start) / 1_000_000, result);
    }

    public static final class EllapsedTime<T> {
        public final long duration;
        public final T result;

        public EllapsedTime(long duration, T result) {
            this.duration = duration;
            this.result = result;
        }

        @Override
        public String toString() {
            return "EllapsedTime{duration=" + duration + ", result=" + result + '}';
        }
    }
}
