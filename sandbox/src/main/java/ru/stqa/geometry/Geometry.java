package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        //Площадь квадрата
        Square.printSquareArea(7.0);
        Square.printSquareArea( 5.0);
        Square.printSquareArea(3.0);

        //Площадь треугольника
        Rectangle.printRectangleArea(5.0, 10.5);
        Rectangle.printRectangleArea(2.0, 18.4);

    }

}
