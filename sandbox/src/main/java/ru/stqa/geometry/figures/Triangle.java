package ru.stqa.geometry.figures;

public class Triangle {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) throws IllegalArgumentException {
        //Проверка на положительные стороны треугольника
        if(sideA <= 0 | sideB <= 0 | sideC <= 0){
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными");
        }
        //Проверка на существование треугольника по теореме Неравенства треугольника
        if ((sideA + sideB <= sideC) || (sideA + sideC <= sideB) || (sideB + sideC <= sideA)){
            throw new IllegalArgumentException("Стороны не могут образовывать треугольник");
        }

        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    //Метод для нахождения периметра Треугольника
    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }

    //Метод для нахождения площади треугольника по формуле Герона
    public double area() {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - this.sideA) * (s - this.sideB) * (s - this.sideC));
    }

    //Геттеры
    public double getSideA(){
        return sideA;
    }

    public double getSideB(){
        return sideB;
    }

    public double getSideC(){
        return  sideC;
    }

}
