package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double sideOne, double sideTwo) {
        String text = String.format(
                "Площадь прямоугольника со сторонами %.1f  и %.1f  = %.1f",sideOne,sideTwo,rectangleArea(sideOne,sideTwo));
        System.out.println(text);

    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
