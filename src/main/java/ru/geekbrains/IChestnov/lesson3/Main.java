package ru.geekbrains.IChestnov.lesson3;

import java.io.*;
import java.util.ArrayList;

/*
 * Урок 3. Сериализация
 * */
public class Main {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Character.getName(i));
        }
        serialObj(list, "filename.txt");

        System.out.println(deSerialObj("filename.txt"));

        serialObj(new Person("Pavel", 39), "person.txt");

        System.out.println(deSerialObj("person.txt"));


    }

    public static void serialObj(Object obj, String filename) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deSerialObj(String filename) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
