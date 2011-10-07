package refactoring;

public class Main {

    private int weight;
    private double height;

    public static void main(String[] args) {
        Main main = new Main();
        main.height = 1.70;
        main.weight = 60;
        System.out.println(main.bmi());
    }

    double bmi() {
        return (weight/(height*height));
    }
}