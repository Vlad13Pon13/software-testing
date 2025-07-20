package ru.stqa.geometry.printCalculate;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class PrintCalculateGeometry {
    public static void printSquareArea(Square square) {
        String text = String.format("Площадь квадрата со стороной %.1f = %.1f ",square.getSide(), square.area());
        System.out.println(text);

    }

    public static void printSquarePerimeter(Square square){
        String text = String.format("Площадь квадрата со стороной %.1f = %.1f ", square.getSide(), square.perimeter());
        System.out.println(text);
    }

    public static void printRectangleArea(Rectangle rectangle) {
        String text = String.format(
                "Площадь прямоугольника с шириной %.1f и высотой %.1f  = %.1f",
                rectangle.getWidth(),rectangle.getHeight(), rectangle.area());
        System.out.println(text);

    }

    public static void printRectanglePerimeter(Rectangle rectangle){
        String text = String.format(
                "Периметер прямоугольника с шириной %.1f и высотой %.1f = %.1f",
                rectangle.getWidth(), rectangle.getHeight(), rectangle.perimeter());
        System.out.println(text);
    }
}
