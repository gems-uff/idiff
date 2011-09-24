package refactoring;

public class Main {
    private int height;

    int getProportion() {
        return (height > 180) ? 2 : 1;
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.height = 178;
        System.out.println(main.getProportion());
    }
}
