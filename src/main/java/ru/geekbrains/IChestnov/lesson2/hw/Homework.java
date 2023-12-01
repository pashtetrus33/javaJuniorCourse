package ru.geekbrains.IChestnov.lesson2.hw;

public class Homework {

    /**
     * Расширить пример с запуском тестов следующими фичами:
     * 1. Добавить аннотации BeforeEach, AfterEach,
     * которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
     * 2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
     * Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
     * Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
     * то порядок вызовов first -> second -> third
     * 3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
     * 4.* При наличии идей, реализовать их и написать об этом в комментарии при сдаче.
     */


    public static void main(String[] args) throws NoSuchMethodException {
        TestProcessor.runTest(MyTest.class);
    }

    static class MyTest {

        @BeforeEach
        void beforeAllTests() {
            System.out.println("метод до запуска всех тестов");
        }

        @AfterEach
        void afterAllTests() {
            System.out.println("метод после запуска всех тестов");
        }

        @Test(order = -2)
        void firstTest() {
            System.out.println("firstTest запущен");
            Assert.assertEquals("Person", "Person");
        }

        @Test
        void secondTest() {
            System.out.println("secondTest запущен");
            Assert.assertTrue(5 > 1);
        }

        @Test(order = 5)
        void thirdTest() {
            System.out.println("thirdTest запущен");
        }

        @Skip
        @Test(order = 8)
        void forthTest() {
            System.out.println("forthTest запущен");
        }
    }
}
