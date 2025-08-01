package ru.stqa.geometry.figures;

import java.util.Objects;

public class Square {

    private double side;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(side, square.side) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(side);
    }

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
