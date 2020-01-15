package com.github.alphaon.euler.problems;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * see: <a href="https://projecteuler.net/problem=24">Lexicographic permutations</a>
 */
public class P024 {

    private static final class Node {
        Node parent;
        String value;
        List<String> symbols;

        Node(Node parent, String value, List<String> symbols) {
            this.parent = parent;
            this.value = value;
            this.symbols = symbols;
        }

        List<String> remove(String symbol) {
            return symbols.stream().filter(it -> !it.equals(symbol)).collect(toList());
        }

        List<Node> children() {
            return symbols.stream().map(s -> new Node(this, s, remove(s))).collect(toList());
        }

        boolean isLeaf() {
            return symbols.isEmpty();
        }

        String path() {
            return Stream.iterate(this, n -> n.parent != null, n -> n.parent).map(n -> n.value).reduce("", (s1, s2) -> s2 + s1);
        }
    }

    private String permutation(List<String> symbols, int nthPermutation) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(new Node(null, "", symbols));
        int permutationNumber = 0;

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            if (n.isLeaf()) {
                permutationNumber++;
                if (permutationNumber == nthPermutation) return n.path();
            } else {
                LinkedList<Node> l = new LinkedList<>();
                n.children().forEach(l::addFirst);
                l.forEach(queue::addFirst);
            }
        }
        throw new ArrayIndexOutOfBoundsException("Could not reach the permutation number " + nthPermutation);
    }


    public String run() {
        var res = permutation(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"), 1_000_000);
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        var p = new P024();
        System.out.println("RES=" + p.run());
    }


}
