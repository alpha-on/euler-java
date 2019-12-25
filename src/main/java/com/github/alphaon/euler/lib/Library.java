package com.github.alphaon.euler.lib;

import java.util.Iterator;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.github.alphaon.euler.lib.Tuples.t2;
import static java.util.stream.Stream.iterate;

public final class Library {

    private final LinkedNode<Integer> knownPrimeNumbers = new LinkedNode<Integer>().append(2, 3, 5, 7, 11, 13, 17, 19);


    private Library() {
    }


    public static Library newInstance() {
        return new Library();
    }

    public boolean isPalyndrom(long number) {
        UnaryOperator<Tuple2<Long, Long>> next = t -> t2(t.a == 0 ? -1 : t.a / 10, (10 * t.b) + (t.a % 10));
        Predicate<Tuple2<Long, Long>> hasNext = t -> t.a >= 0;

        var n = Math.abs(number);
        var mirror = iterate(t2(n, 0L), hasNext, next).mapToLong(Tuple2::b).max().orElseThrow();
        return mirror == n;
    }

    private boolean isPrimeNumber(Integer v) {
        var limit = Math.sqrt(v);
        return knownPrimeNumbers.stream().takeWhile(it -> it <= limit).noneMatch(it -> v % it == 0);
    }

    public IntStream streamPrimeNumbers() {
        return IntStream.generate(primeNumbers());
    }

    public IntSupplier primeNumbers() {
        return new IntSupplier() {
            private LinkedNode<Integer>.Node node = knownPrimeNumbers.first;

            private int updateNext() {
                int nextPrime = knownPrimeNumbers.last.v + 2;
                for (; !isPrimeNumber(nextPrime); nextPrime += 2) ;
                knownPrimeNumbers.append(nextPrime);
                return nextPrime;
            }

            @Override
            public int getAsInt() {
                if (node != null) {
                    int ret = node.v;
                    node = node.next;
                    return ret;
                } else {
                    return updateNext();
                }
            }
        };
    }

    private static final class LinkedNode<T> implements Iterable<T> {
        private Node first;
        private Node last;

        private final class Node {
            private final T v;
            private Node next;

            private Node(T v) {
                this.v = v;
            }
        }

        private LinkedNode<T> append(T... values) {
            Stream.of(values).forEach(v -> {
                if (first == null) {
                    first = last = new Node(v);
                } else {
                    last.next = new Node(v);
                    last = last.next;
                }
            });
            return this;
        }

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node n = first;

                @Override
                public boolean hasNext() {
                    return n != null;
                }

                @Override
                public T next() {
                    T ret = n.v;
                    n = n.next;
                    return ret;
                }
            };
        }

        private Stream<T> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }
    }
}
