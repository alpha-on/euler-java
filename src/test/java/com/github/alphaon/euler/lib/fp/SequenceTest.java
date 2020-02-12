package com.github.alphaon.euler.lib.fp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SequenceTest {

    @Test
    void should_convert_to_list() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4);

        // When:
        var actual = seq.toList();

        // Then
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, actual.toArray());
    }

    @Test
    void should_limit_sequence() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4, 5);

        // When:
        var actual = seq.limit(3).toList();

        // Then:
        assertArrayEquals(new Integer[]{1, 2, 3}, actual.toArray());
    }

    @Test
    void should_get_first_item_matching_predicate() {
        // Given:
        var seq = Sequence.from(1, 3, 7, 4, 5, 2);

        // When:
        var actual = seq.first(v -> v % 2 == 0);

        // Then:
        assertEquals(4, actual.orElseThrow());
    }

    @Test
    void should_map_content() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4);

        // When:
        var actual = seq.map(it -> it * 2).toList();

        // Then:
        assertArrayEquals(new Integer[]{2, 4, 6, 8}, actual.toArray());
    }

    @Test
    void should_apply_map_in_given_order() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4);

        // When:
        var actual = seq.map(it -> it * 2).map(it -> it + 1).toList();

        // Then:
        assertArrayEquals(new Integer[]{3, 5, 7, 9}, actual.toArray());
    }

    @Test
    void should_filter_items() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4, 5, 6);

        // When:
        var actual = seq.filter(it -> it % 2 == 0).toList();

        // Then:
        assertArrayEquals(new Integer[]{2, 4, 6}, actual.toArray());
    }

    @Test
    void should_count_items() {
        // Given:
        var seq = Sequence.from(1, 2, 3, 4, 5, 6);

        // When:
        var actual = seq.count();

        // Then:
        assertEquals(6, actual);
    }

    @Test
    void should_unique_remove_duplicate() {
        // Given:
        var seq = Sequence.from(1, 2, 1, 1, 2, 3, 3, 3, 4);

        // When:
        var actual = seq.unique().toList();

        // Then:
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, actual.toArray());
    }

    @DisplayName("Monad laws")
    @Nested
    class MonadLaw {
        @Test
        @DisplayName("From is the default constructor")
        void should_From_be_the_constructor() {
            // Given:
            var seq = Sequence.from(1, 2);

            // When:
            var actual = seq.toList();

            // Then:
            assertArrayEquals(new Integer[]{1, 2}, actual.toArray());
        }

        @Test
        @DisplayName("Left identity")
        void left_identity() {
            // Given:
            Function<Integer, Sequence<Integer>> f = n -> Sequence.from(n, 2 * n, 3 * n);

            // When:
            var actual = Sequence.from(1).flatMap(f).toList();

            // Then:
            var expected = f.apply(1).toList();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        @DisplayName("Right identity")
        void right_identity() {
            // Given:
            Function<Integer, Sequence<Integer>> f = Sequence::from;

            // When:
            var actual = Sequence.from(1).flatMap(f).toList();

            // Then:
            var expected = Sequence.from(1).toList();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }


        @Test
        @DisplayName("Associativity")
        void associativity() {
            // Given:
            Function<Integer, Sequence<Integer>> f = n -> Sequence.from(n + 1);
            Function<Integer, Sequence<Integer>> g = n -> Sequence.from(2 * n);

            // When:
            var actual = Sequence.from(1, 2).flatMap(f).flatMap(g).toList();

            // Then:
            var expected = Sequence.from(1, 2).flatMap(x -> f.apply(x).flatMap(g)).toList();

            assertArrayEquals(expected.toArray(), actual.toArray());
        }
    }


}