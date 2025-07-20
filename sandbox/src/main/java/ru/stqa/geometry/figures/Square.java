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


    public double getSide(){
        return side;
    }

    public void setSide(double side){
        this.side=side;
    }

}
