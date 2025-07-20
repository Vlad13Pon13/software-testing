package ru.stqa.geometry.figures;

public class Square {

    private double side;

    public Square (double side){
        this.side = side;
    }

    public double area (){
        return this.side * this.side;

    }

    public double perimeter(){
        return this.side * 4;
    }


    public static double area(double a) {
        return a * a;
    }

    public double getSide(){
        return side;
    }

    public void setSide(double side){
        this.side=side;
    }

}
