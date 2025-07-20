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
    @MethodSource(("providerPositiveTriangle"))
    public void calculateTriangleAreTest(double sideA, double sideB, double sideC) {
        double triangleAre = new Triangle(3.0, 4.0, 5.0).area();
        Assertions.assertEquals(6.0, triangleAre, "Площадь треугольника расчитана некорректно");
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
                Arguments.of(3.0, 4.0, 5.0),
                Arguments.of(5.0, 4.0, 7.0),
                Arguments.of(6.0, 9.0, 8.0)
        );

    }

}
