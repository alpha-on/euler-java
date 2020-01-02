package com.github.alphaon.euler.problems;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

/**
 * see: <a href="https://projecteuler.net/problem=19">Counting Sundays</a>
 */
public class P019 {

    public String run() {
        var start = LocalDate.of(1901, Month.JANUARY, 1);
        var end = LocalDate.of(2000, Month.DECEMBER, 31);
        var res = Stream.iterate(start, d -> d.compareTo(end) <= 0, d -> d.plusDays(1))
                .filter(d -> d.getDayOfMonth() == 1 && d.getDayOfWeek() == DayOfWeek.SUNDAY)
              //  .peek(System.out::println)
                .count();
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println("RES=" + new P019().run());
    }


}
