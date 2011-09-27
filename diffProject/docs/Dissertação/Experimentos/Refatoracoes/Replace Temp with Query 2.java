package refactoring;

public class Main {

    double weight = 73.4;
    double height = 1.78;

    public static void main(String args[]) {
        Main main = new Main();
        main.getBMI();
    }

    private void getBMI() {
        if (bmi() > 24) {
            System.out.println("Normal weight - " + bmi());
        } else {
            System.out.println("Overweight - " + bmi());
        }
    }

    private double bmi() {
        return weight / (height * height);
    }
}
