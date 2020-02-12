package com.github.alphaon.euler.lib.fp;

import java.util.Iterator;
import java.util.function.Function;

public final class Sequence<A> {
    private final Iterator<A> src;

    private Sequence(Iterator<A> src) {
        this.src = src;
    }

    public <B> Sequence<B> map(Function<A, B> ff) {
        return new Sequence<>(new MappedIter<>(ff));
    }

    private final class MappedIter<B> implements Iterator<B> {
        private final Function<A, B> map;

        private MappedIter(Function<A, B> map) {
            this.map = map;
        }

        @Override
        public boolean hasNext() {
            return src.hasNext();
        }

        @Override
        public B next() {
            return map.apply(src.next());
        }
    }
    private final class FlatMapped<B> implements Iterator<B> {
        private boolean eos = false;
        private Iterator<B> currIter;

        @Override
        public boolean hasNext() {
            return false;
        }

        private boolean tryAdvance() {
            if (currIter !=)
        }
    }
}
