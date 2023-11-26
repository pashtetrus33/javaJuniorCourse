package ru.geekbrains.SBairakovsky.lesson1.hw;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 1001))
                .limit(5).boxed().toList();

        System.out.println(list);

        System.out.println("Сумма четных чисел списка: " + list.stream().filter(it -> it % 2 == 0).peek(System.out::println).reduce(0, Integer::sum));


    }
}
