package ru.geekbrains.IChestnov.lesson4.hw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.util.List;

public class HomeworkJpa {

    /**
     * 2. С помощью JPA(Hibernate) выполнить:
     * 2.1 Описать сущность Book из пункта 1.1
     * 2.2 Создать Session и сохранить в таблицу 10 книг
     * 2.3 Выгрузить список книг какого-то автора
     * <p>
     * 3.* Создать сущность Автор (id bigint, name varchar), и в сущности Book сделать поле типа Author (OneToOne)
     * 3.1 * Выгрузить Список книг и убедиться, что поле author заполнено
     * 3.2 ** В классе Author создать поле List<Book>, которое описывает список всех книг этого автора. (OneToMany)
     */

    public final static String AUTHOR_SEARCH = "'L.Tolstoy'";

    public static void main(String[] args) throws SQLException {
        // JPA Java Persistence API - набор правил (соглашений) по реализации доменной модели
        // Entity

        // Hibernate -> одна из реализаций стандарта JPA
        // EclipseLink

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        booksPersist(sessionFactory);
        readAllBooks(sessionFactory);
        searchByAuthor(sessionFactory, AUTHOR_SEARCH);
        sessionFactory.close();
    }

    //2.2 Создать Session и сохранить в таблицу 10 книг
    private static void booksPersist(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Author aPushkin = new Author("A.Pushkin");
            Author fDostoevksy = new Author("F.Dostoevsky");
            Author lTolstoy = new Author("L.Tolstoy");
            Author nGogol = new Author("N.Gogol");
            Author mBulgakov = new Author("M.Bulgakov");
            Author mSholokhov = new Author("M.Sholokhov");
            Author iTurgenev = new Author("I.Turgenev");
            Author iGoncharov = new Author("I.Goncharov");
            List<Author> authors = List.of(aPushkin, fDostoevksy, lTolstoy, nGogol, mBulgakov, mSholokhov, iTurgenev, iGoncharov);
            authors.forEach(session::persist);
            List<Book> books = List.of(
                    new Book("Eugene Onegin", aPushkin),
                    new Book("The Brothers Karamazov", fDostoevksy),
                    new Book("War and Peace", lTolstoy),
                    new Book("Dead Souls", nGogol),
                    new Book("The Master and Margarita", mBulgakov),
                    new Book("Fathers and Sons", iTurgenev),
                    new Book("And Quiet Flows the Don", mSholokhov),
                    new Book("Oblomov", iGoncharov),
                    new Book("Crime and punishment", fDostoevksy),
                    new Book("War and Peace", lTolstoy),
                    new Book("Anna Karenina", lTolstoy)
            );


            books.forEach(session::persist);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.3 Выгрузить список книг какого-то автора
    private static void searchByAuthor(SessionFactory sessionFactory, String searchString) {
        System.out.println("\nПоиск книг автора " + searchString + ":");
        try (Session session = sessionFactory.openSession()) {
            List<Book> books = session.createQuery("select b from Book b where author.name = " + searchString, Book.class)
                    .getResultList();

            System.out.println(books);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.1 Выгрузить Список книг и убедиться, что поле author заполнено
    private static void readAllBooks(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("\nЧтение записей из таблицы Book:");
            String sql = "From " + Book.class.getSimpleName();

            List<Book> books = session.createQuery(sql).list();

            System.out.println("books.size = " + books.size());
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
