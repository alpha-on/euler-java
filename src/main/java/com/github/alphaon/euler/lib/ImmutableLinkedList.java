package com.github.alphaon.euler.lib;

import java.util.Iterator;

public class ImmutableLinkedList<T> {

    private final class Node<T> {
        private final T value;
        private final Node<T> first;
        private final Node<T> last;
        private final int size;

        private Node(T value, Node<T> first, Node<T> last) {
            this.value = value;
            this.first = first == null ? this : first;
            this.last = last == null ? this : last;
            this.size = first == null ? 1 : 1 + first.size;
        }

        private Node(T value) {
            this(value, null, null);
        }

        private Node<T> succ(Node<T> node) {
            if (node == null) return first;
            if (node == this) return null;
            if (node == last) return this;
            else return first.succ(node);
        }

        private Iterator<T> iter() {
            return new Iterator<T>() {
                int counter = 0;
                Node<T> previous = null;

                @Override
                public boolean hasNext() {
                    return counter < size;
                }

                @Override
                public T next() {
                    if (counter >= size) throw new ArrayIndexOutOfBoundsException("No more elements");
                    if (previous == null) previous = first;

                    return previous.value;
                }
            };
        }

    }
}
