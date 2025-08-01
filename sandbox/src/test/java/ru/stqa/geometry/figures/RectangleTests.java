package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    @DisplayName("Проверка вычисления площади прямоугольника")
    void calculateRectangleAreaTest(){
        double rectangleAre = new Rectangle(5.0, 10.0).area();
        Assertions.assertEquals(50.0, rectangleAre, "Площадь расчитана некорркектно");
    }

    @Test
    @DisplayName("Проверка вычисления периметра прямоугольника")
    void calculateRectanglePerimeterTest(){
        double rectanglePerimeter = new Rectangle(13.0, 20.0).perimeter();
        Assertions.assertEquals(66.0, rectanglePerimeter, "Периметр расчитан некорркектно");
    }

    //TODO дописать позитивные и негативыне тесты с параматеризацией


}
