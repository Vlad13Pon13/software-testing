package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;
import ru.stqa.geometry.printCalculate.PrintCalculateGeometry;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {

    public static void main(String[] args) {
        /*
        //Площадь фигур:
        // Площадь квадрата
        PrintCalculateGeometry.printSquareArea(new Square(8.0));
        //Площадь прямоугольника
        PrintCalculateGeometry.printRectangleArea(new Rectangle(5.0, 20.0));
        //Площадь треугольника
        PrintCalculateGeometry.printTriangleArea(new Triangle(3.0, 4.0, 5.0));


        //Периметр фигур:
        //Периметер квадрата
        PrintCalculateGeometry.printSquarePerimeter(new Square(10.0));
        //Периметер Прямоугольника
        PrintCalculateGeometry.printRectanglePerimeter(new Rectangle(5.0, 20.0));
        //Периметр треугольника
        PrintCalculateGeometry.printTrianglePerimetr(new Triangle(3.0, 4.0, 5.0));

         */

        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(1000.0));
        var squares = Stream.generate(randomSquare).limit(10);


  //      Consumer<Square>  print = (square) ->{
  //          PrintCalculateGeometry.printSquareArea(square);
  //          PrintCalculateGeometry.printSquarePerimeter(square);
  //      };
        squares.peek(PrintCalculateGeometry::printSquareArea).forEach(PrintCalculateGeometry::printSquarePerimeter);









    }

}
