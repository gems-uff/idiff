package refactoring;

public class Main {

    public static void main(String[] args) {
        double weight = 73.4;
        double height = 1.78;

        final double height2 = height * height;
        System.out.println(height2);
        final double bmi = weight/height2;
        System.out.println(bmi);
    }
}