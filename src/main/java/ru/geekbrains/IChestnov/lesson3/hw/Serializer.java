package ru.geekbrains.IChestnov.lesson3.hw;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

public class Serializer {

    public static <T extends Serializable> String upload(T item) {
        Path myFile = Path.of(item.getClass().toString() + "_" + UUID.randomUUID());
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Files.createFile(myFile)))) {
            objectOutputStream.writeObject(item);
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных!");
            //e.printStackTrace();
        }
        return String.valueOf(myFile);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Optional<T> download(String filename) {
        Optional<T> deserializedItem = Optional.empty();
        Path myFile = Path.of(filename);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(myFile))) {
            deserializedItem = Optional.of((T) objectInputStream.readObject());
            Files.delete(myFile);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс для десериализации не найден!");
        } catch (IOException e) {
            System.out.println("Файл " + myFile + " не найден!");
            e.printStackTrace();
        }
        return deserializedItem;
    }
}


