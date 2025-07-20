public class Geometry {
    public static void main(String[] args) {
        //Площадь квадрата
        printSquareArea(7.0);
        printSquareArea( 5.0);
        printSquareArea(3.0);

        //Площадь треугольника
        printRectangelArea(5.0, 10.5);
        printRectangelArea(2.0, 18.4);

    }

    private static void printRectangelArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangelArea(a,b));

    }

    private static double rectangelArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {

        System.out.println("Площадь квадрата со стороной " + side + " = " + squareAre(side));

    }

    private static double squareAre(double a) {
        return a * a;
    }
}
