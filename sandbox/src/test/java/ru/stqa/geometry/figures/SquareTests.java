package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    @DisplayName("Проверка вычисления площади квадрата")
    void calculateSquareAreaTest(){
        Square square = new Square(5.0);
        double squareArea = square.area();
        Assertions.assertEquals(25.0, squareArea, "Площадь квадрата расчитана некорректно");

    }

    @Test
    @DisplayName("Проверка вычисления периметра квадрата")
    void calculatePerimeterSquareTest(){
        Square square = new Square( 10.0);
        double squarePerimeter = square.perimeter();
        Assertions.assertEquals(40.0, squarePerimeter, "Периметр квадрата расчитан некорректно");

    }


}
