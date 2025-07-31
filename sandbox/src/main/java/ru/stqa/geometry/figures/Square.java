package ru.stqa.geometry.figures;

public class Square {

    private double side;

    public Square (double side){
        if (side < 0){
            throw new IllegalArgumentException("Сторона квадрата не может быть отрицательной");
        }
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
