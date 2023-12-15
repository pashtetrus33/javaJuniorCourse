package ru.geekbrains.IChestnov.lesson3;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
