package ru.geekbrains.IChestnov.lesson1.hw;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Homework {

    /**
     * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
     * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
     * <p>
     * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
     * 1.1 Найти максимальное
     * 1.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
     * 1.3 Найти количество чисел, квадрат которых меньше, чем 100_000
     * <p>
     * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
     * 2.1 Создать список из 10-20 сотрудников
     * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
     * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
     * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
     * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
     */

    public static void main(String[] args) {
        int VALUES_AMOUNT = 1000;
        int MIN_INCLUSIVE_BOUND = 1;
        int MAX_INCLUSIVE_BOUND = 1_000_000;

        //* 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Integer> data = integerGenerator(VALUES_AMOUNT, MIN_INCLUSIVE_BOUND, MAX_INCLUSIVE_BOUND);
        //printResult(data, "DATA");

        //* 1.1 Найти максимальное
        printResult(getMaxValue(data), "TASK 1.1 MAX VALUE");

        //* 2.2 Найти все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
        printResult(calculateTask2(data), "TASK 1.2");

        //2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
        printResult(calculateTask3(data), "TASK 1.3");

        //* 2.1 Создать список из 10-20 сотрудников
        List<Employee> employees = List.of(
                new Employee("Ivan", 25, 125_000, "IT"),
                new Employee("Mariia", 33, 100_000, "Sales"),
                new Employee("Oleg", 21, 45_000, "Management"),
                new Employee("Pavel", 39, 99_000, "IT"),
                new Employee("Katya", 38, 45_000, "Accounts"),
                new Employee("Roman", 55, 48_000, "Sales"),
                new Employee("Ivan", 62, 155_000, "IT"),
                new Employee("Mikhail", 30, 45_000, "Accounts"),
                new Employee("Natalia", 27, 35_000, "Sales"),
                new Employee("Pavel", 25, 60_000, "Sales"),
                new Employee("Semen", 33, 8_000, "Accounts"),
                new Employee("Aleksey", 40, 45_000, "Management"),
                new Employee("Oleg", 51, 99_000, "Management"),
                new Employee("Mariia", 24, 45_000, "Sales"),
                new Employee("John", 25, 9_000, "Management"));
        System.out.println("===================== TASK 2.1 ============================");
        System.out.println(employees);


        //* 2.2 Вывести список всех различных отделов (department) по списку сотрудников
        System.out.println("\n===================== TASK 2.2 ============================");
        employees.stream().map(Employee::getDepartment)
                .distinct()
                .forEach(System.out::println);

        //* 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        System.out.println("\n===================== TASK 2.3 ============================");
        employees.stream()
                .filter(it -> it.getSalary() < 10_000)
                .forEach(it -> it.setSalary(it.getSalary() * 1.2));
        System.out.println(employees);

        // * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
        System.out.println("\n===================== TASK 2.4 ============================");
        Map<String, List<Employee>> employeeMap = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(employeeMap);

        //* 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        System.out.println("\n===================== TASK 2.5 ============================");
        Map<String, Double> meanSalary = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(meanSalary);
    }


    //* 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
    private static List<Integer> integerGenerator(int amount, int min, int max) {
        return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(min, max + 1))
                .limit(amount).boxed().toList();
    }

    //* 1.1 Найти максимальное
    private static int getMaxValue(List<Integer> data) {
        return data.stream().max(Integer::compareTo).orElse(-1);
    }

    //* 2.2 Найти все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
    private static long calculateTask2(List<Integer> data) {
        return data.stream()
                .filter(it -> it > 500000)
                .map(it -> (it * 5 - 150))
                //.peek(it -> System.out.print(it + " "))
                .reduce(0, Integer::sum);
    }

    //2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
    private static long calculateTask3(List<Integer> data) {
        return data.stream().filter(it -> Math.pow(it, 2) < 100_000).count();
    }

    private static void printResult(List<Integer> data, String title) {
        System.out.println("===================== " + title + " ============================");
        data.forEach(it -> System.out.print(it + " "));
        System.out.println("\n=====================================================");
    }

    private static void printResult(long value, String title) {
        System.out.println("===================== " + title + " ============================");
        System.out.print(value);
        System.out.println("\n=====================================================");
    }
}
