package ru.stqa.geometry.figures;

public class Rectangle {

    private double width;
    private double height;

    public Rectangle( double width, double height){
        this.height=height;
        this.width=width;
    }

    public double getWidth(){
        return width;
    }

    public void setWidth( double width){
        this.width = width;
    }

    public double getHeight(){
        return height;
    }

    public void setHeight(double height){
        this.height=height;
    }

    public double area(){
        return this.width * this.height;
    }

    public double perimeter(){
        return 2 * (this.height + this.width);
    }




}
