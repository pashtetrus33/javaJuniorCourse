package ru.geekbrains.IChestnov.lesson2.hw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestProcessor {

    /**
     * Данный метод находит все void методы без аргументов в классе, и запускеет их.
     * <p>
     * Для запуска создается тестовый объект с помощью конструткора без аргументов.
     */
    public static void runTest(Class<?> testClass) throws NoSuchMethodException {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        List<Method> methods = new ArrayList<>();
        // Домашнее задание пункт 3
        Arrays.stream(testClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(Test.class) && !m.isAnnotationPresent(Skip.class))
                .peek(TestProcessor::checkTestMethod)
                .forEach(methods::add);

        // Домашнее задание пункт 2
        methods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).order()));

        // Домашнее задание пункт 1
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(testClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(BeforeEach.class))
                .peek(TestProcessor::checkTestMethod)
                .forEach(m -> methods.add((count.getAndIncrement()), m));

        Arrays.stream(testClass.getDeclaredMethods()).filter(m -> m.isAnnotationPresent(AfterEach.class))
                .peek(TestProcessor::checkTestMethod)
                .forEach(methods::add);
        methods.forEach(it -> runTest(it, testObj));
    }

    private static void checkTestMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Метод \"" + method.getName() + "\" должен быть void и не иметь параметров");
        }
    }

    private static void runTest(Method testMethod, Object testObj) {
        try {
            testMethod.invoke(testObj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
        } catch (AssertionError e) {

        }
    }

}
