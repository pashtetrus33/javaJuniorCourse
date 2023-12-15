package ru.geekbrains.IChestnov.lesson4.hw;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class HomeworkJdbc {

    /**
     * Задания необходимо выполнять на ЛЮБОЙ СУБД (postgres, mysql, sqllite, h2, ...)
     * 1. С помощью JDBC выполнить:
     * 1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
     * 1.2 Добавить в таблицу 10 книг
     * 1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
     */

    public final static String AUTHOR_SEARCH = "F.Dostoevsky";

    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection.isValid(0) = " + connection.isValid(0));
            prepareTables(connection);
            insertData(connection);
            executeSelect(connection, AUTHOR_SEARCH);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:h2:./library");
        return ds;
    }

    //1.1 Создать таблицу book с колонками id bigint, name varchar, author varchar, ...
    private static void prepareTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    drop table if exists book;
                    create table book (
                      id bigint auto_increment,
                      name varchar(255),
                      author varchar(255)
                    )
                    """);
        }
    }

    //1.2 Добавить в таблицу 10 книг
    private static void insertData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int updatedRows = statement.executeUpdate("""
                    insert into book(name, author) 
                    values('Eugene Onegin','A.Pushkin'),
                          ('The Brothers Karamazov', 'F.Dostoevsky'),
                          ('Crime and Punishment','F.Dostoevsky'),
                          ('War and Peace', 'L.Tolstoy'),
                          ('Dead Souls','N.Gogol'),
                          ('The Master and Margarita','M.Bulgakov'),
                          ('Fathers and Sons','I.Turgenev'),
                          ('And Quiet Flows the Don','M.Sholokhov'),
                          ('Oblomov','I.Goncharov'),
                          ('Anna Karenina', 'L.Tolstoy')""");
            System.out.println(updatedRows);
        }
    }

    //1.3 Сделать запрос select from book where author = 'какое-то имя' и прочитать его с помощью ResultSet
    private static void executeSelect(Connection connection, String author) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from book where author = ?");
        ps.setString(1, author);
        ResultSet resultSet = ps.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            author = resultSet.getString("author");

            System.out.println("[" + ++count + "] id = " + id + ", name = " + name + " author = " + author);
        }
    }
}
