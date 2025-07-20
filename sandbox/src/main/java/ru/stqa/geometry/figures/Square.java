package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {

        System.out.println("Площадь квадрата со стороной " + side + " = " + squareAre(side));

    }

    private static double squareAre(double a) {
        return a * a;
    }
}
