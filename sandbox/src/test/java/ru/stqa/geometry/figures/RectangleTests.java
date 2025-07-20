package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    @DisplayName("Проверка вычисления площади прямоугольника")
    void calculateRectangleAreaTest(){
        double rectangleArea = Rectangle.area(5.0, 10.0);
        Assertions.assertEquals(50.0, rectangleArea, "Площадь расчитана некорректно");
    }

    @Test
    @DisplayName("Проверка вычисления периметра прямоугольника")
    void calculateRectanglePerimeterTest(){
        double rectanglePerimeter = Rectangle.perimeter(8.0, 5.0);
        Assertions.assertEquals(26.0, rectanglePerimeter, "Перемитер расчитан некорректно");
    }


}
