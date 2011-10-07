package refactoring;

public class Main {
    private int height;

    int getProportion() {
        return (isTaller()) ? 2 : 1;
    }

    private boolean isTaller() {
        return height > 180;
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.height = 178;
        System.out.println(main.getProportion());
    }
}
