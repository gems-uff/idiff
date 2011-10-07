package refactoring;

public class Main {
    public static void main(String[] args) {
        double weight = 73.4;
        double height = 1.78;

        double temp = height * height;
        System.out.println(temp);
        temp = weight / temp;
        System.out.println(temp);
    }
}