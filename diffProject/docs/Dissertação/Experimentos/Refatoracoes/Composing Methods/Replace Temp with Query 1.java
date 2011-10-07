package refactoring;

public class Main {

    double weight = 73.4;
    double height = 1.78;

    public static void main(String args[]) {
        Main main = new Main();
        main.getBMI();
    }

    private void getBMI() {
        double bmi = weight / (height * height);
        if (bmi > 24) {
            System.out.println("Normal weight - " + bmi);
        } else {
            System.out.println("Overweight - " + bmi);
        }
    }
}
