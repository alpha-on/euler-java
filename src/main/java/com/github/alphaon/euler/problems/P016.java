package com.github.alphaon.euler.problems;


import java.util.stream.IntStream;

/**
 * see: <a href="https://projecteuler.net/problem=16">Power digit sum</a>
 */
public class P016 {

    private int computeDigits(int[] digits, final int _size) {
        int previous = 0;
        int size = _size;
        for (int i = 0; i < size; i++) {
            previous = (digits[i] << 1) + previous / 10;
            digits[i] = previous % 10;
        }
        if (previous >= 10) {
            digits[size++] = previous / 10;
        }
        return size;
    }

    public String run() {
        int power = 1_000;
        int n = (int) (power * Math.log(2) / Math.log(10) + 1);
        int[] digits = new int[n];
        digits[0] = 1;
        int size = 1;
        for (int i = 0; i < power; i++) {
            size = computeDigits(digits, size);
        }
        var res = IntStream.of(digits).sum();
        return String.valueOf(res);
    }


    public static void main(String[] args) {
        System.out.println("RES=" + new P016().run());
    }


}
