package refactoring;

public class Main {

    public static double temperature = 37;
    public static String time = "16:00";

    public static void main(String[] args) {

        if ((temperature == 36.3 && time.equals("06:00")) || (temperature == 37.0 && time.equals("16:00"))) {
            System.out.println("Normal Temperature");
        } else {
            System.out.println("Fever");
        }
    }
}