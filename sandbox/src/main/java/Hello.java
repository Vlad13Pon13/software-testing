
public class Hello {

    public static void main(String[] args) {
        try {
            int a = 1;
            int b = 1;
            int z = divide(a,b);

            System.out.println(z);
        } catch (ArithmeticException exception) {
            System.out.println("Division by zero is not allowed");
        }

    }

    private static int divide(int a, int b) {
        return a / b;
    }
}
