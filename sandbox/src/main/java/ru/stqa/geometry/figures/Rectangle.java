package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double sideOne, double sideTwo) {
        System.out.println("Площадь прямоугольника со сторонами " + sideOne + " и " + sideTwo + " = " + rectangleArea(sideOne,sideTwo));

    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
