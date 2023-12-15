package ru.geekbrains.IChestnov.lesson4.hw;

import jakarta.persistence.*;

//2.1 Описать сущность Book из пункта 1.1
@Entity
@Table(name = "book")
// публичный конструктор без аргументов
// геттеры и сеттеры для каждого поля
// у каждой сущности (entity) есть первичный ключ
public class Book {

    public Book() {

    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    // Unidirectional
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", author='" + author.getName() + '\'' +
               '}';
    }
}
