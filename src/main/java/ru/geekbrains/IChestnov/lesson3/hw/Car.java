package ru.geekbrains.IChestnov.lesson3.hw;

import java.io.Serializable;

public record Car(String brand, String model, int year) implements Serializable {
}
