package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.printCalculate.PrintCalculateGeometry;

public class Geometry {
    public static void main(String[] args) {
        //Площадь квадрата
        PrintCalculateGeometry.printSquareArea(new Square(8.0));
        //Площадь прямоугольника
        PrintCalculateGeometry.printRectangleArea(new Rectangle(5.0, 20.0));

        //Периметер квадрата
        PrintCalculateGeometry.printSquarePerimeter(new Square(10.0));
        //Периметер Прямоугольника
        PrintCalculateGeometry.printRectanglePerimeter(new Rectangle(5.0, 20.0));



    }

}
