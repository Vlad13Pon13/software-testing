package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TriangleTests {
    @ParameterizedTest
    @DisplayName("Позитивный тест: Проверка нахождения площади треугольника")
    @MethodSource("providerPositiveTriangle")
    public void calculateTriangleAreTest(double sideA, double sideB, double sideC, double result) {
        double triangleAre = new Triangle(sideA, sideB, sideC).area();
        Assertions.assertEquals(result, triangleAre, "Площадь треугольника расчитана некорректно");
    }

    @ParameterizedTest
    @DisplayName("Негативный тест: проверка исключения на отрицательную сторону треугольника")
    @MethodSource("providerNegativeSide")
    public void negativeTriangleTest(double sideA, double sideB, double sideC) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double triangleArea = new Triangle(sideA, sideB, sideC).area();
        });
    }

    @ParameterizedTest
    @DisplayName("Негативный тест: проверка исключения по теореме неравенства Треугольников")
    @MethodSource("providerTriangleInequality")
    public void negativeTriangleInequality(double sideA, double sideB, double sideC){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double triangleArea = new Triangle(sideA, sideB, sideC).area();
        });

    }

    @Test
    @DisplayName("Проверка на сравнение объектов")
    public void compareTriangle(){
        Triangle firstTriangle = new Triangle( 3.0, 4.0, 5.0);
        Triangle secondTriangle = new Triangle( 4.0, 3.0, 5.0);
        Triangle thirdTriangle = new Triangle(5.0, 4.0, 3.0);

        Assertions.assertTrue(firstTriangle.equals(secondTriangle));
        Assertions.assertTrue(firstTriangle.equals(thirdTriangle));
        Assertions.assertTrue(thirdTriangle.equals(secondTriangle));

    }

    static Stream<Arguments> providerNegativeSide() {
        return Stream.of(
                Arguments.of(-3.0, 4.0, 5.0),
                Arguments.of(3.0, -4.0, 5.0),
                Arguments.of(3.0, 4.0, -5.0));
    }

    static Stream<Arguments> providerTriangleInequality() {
        return Stream.of(
                Arguments.of(1.0, 3.0, 5.0),
                Arguments.of(5.0, 3.0, 1.0),
                Arguments.of(3.0, 1.0, 5.0)
        );

    }

    static Stream<Arguments> providerPositiveTriangle(){
        return Stream.of(
                Arguments.of(3.0, 4.0, 5.0, 6.0),
                Arguments.of(10.0, 12.0, 10.0, 48.0),
                Arguments.of(25.0, 20.0, 15.0, 150.0)
        );

    }

}
