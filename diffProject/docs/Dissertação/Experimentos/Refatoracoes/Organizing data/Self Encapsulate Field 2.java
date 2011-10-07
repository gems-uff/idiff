package refactoring;

public class Main {

    private int weight;
    private double height;

    public static void main(String[] args) {
        Main main = new Main();
        main.setHeight(1.70);
        main.setWeight(60);
        System.out.println(main.bmi());
    }

    double bmi() {
        return (getWeight() / (getHeight() * getHeight()));
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}