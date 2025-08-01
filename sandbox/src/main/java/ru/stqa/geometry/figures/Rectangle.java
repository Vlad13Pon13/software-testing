package ru.stqa.geometry.figures;

public record Rectangle(double width, double height) {

    public Rectangle{
        if (width < 0 || height < 0){
            throw new IllegalArgumentException("Сторона прямоугольника не можем быть отрницательной");
        }
    }

    public double area(){
        return this.width * this.height;
    }

    public double perimeter(){
        return 2 * (this.height + this.width);
    }

}
