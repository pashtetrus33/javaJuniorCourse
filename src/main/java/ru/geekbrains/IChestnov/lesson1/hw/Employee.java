package ru.geekbrains.IChestnov.lesson1.hw;

//2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
public class Employee {
    private final String name;
    private final int age;
    private double salary;
    private final String department;

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "\nname: " + name + ", age:" + age + ", salary: " + salary + ", department: " + department;
    }
}
