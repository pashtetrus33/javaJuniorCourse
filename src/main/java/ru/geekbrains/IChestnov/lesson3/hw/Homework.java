package ru.geekbrains.IChestnov.lesson3.hw;


import java.util.Optional;

public class Homework {

    /**
     * Написать класс с двумя методами:
     * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
     * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
     * <p>
     * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
     */

    public static void main(String[] args) {
        String filename = Serializer.upload(new Car("BMW", "X5", 2022));
        Optional<Car> target = Serializer.download(filename);
        target.ifPresent(System.out::println);
    }
}
