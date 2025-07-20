package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    @DisplayName("Проверка вычисления площади квадрата")
    void calculateSquareAreaTest(){
        double squareArea = Square.area(10.0);
        Assertions.assertEquals(100.0, squareArea, "Площадь квадрата расчитана некорректно");

    }

    @Test
    @DisplayName("Проверка вычисления периметра квадрата")
    void calculatePerimeterSquareTest(){
        double squarePerimeter = Square.perimeter(5.0);
        Assertions.assertEquals(20.0, squarePerimeter, "Периметр квадрата расчитан некорректно");
    }


}
