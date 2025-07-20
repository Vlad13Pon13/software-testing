package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double sideOne, double sideTwo) {
        String text = String.format(
                "Площадь прямоугольника со сторонами %.1f  и %.1f  = %.1f",sideOne,sideTwo, area(sideOne,sideTwo));
        System.out.println(text);

    }

    public static double area(double a, double b) {
        return a * b;
    }

    public static double perimeter(double a , double b) {
        return (a + b) * 2;
    }


}
