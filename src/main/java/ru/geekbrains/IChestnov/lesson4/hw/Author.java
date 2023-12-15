package ru.geekbrains.IChestnov.lesson4.hw;

import jakarta.persistence.*;

import java.util.List;

//3.Создать сущность Автор (id bigint, name varchar)
@Entity
@Table(name = "author")
// публичный конструктор без аргументов
// геттеры и сеттеры для каждого поля
// у каждой сущности (entity) есть первичный ключ
public class Author {

    public Author() {

    }

    public Author(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    List<Book> bookList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
