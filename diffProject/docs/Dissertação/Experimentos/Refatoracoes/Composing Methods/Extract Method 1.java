package refactoring;

public class Main {

    public void printFeatures(String name, int age) {
        print();
        //print details
        System.out.println("name" + name);
        System.out.println("age" + age);
    }

    private void print() {
        System.out.println("Personal Details");
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.printFeatures("Fernanda Floriano", 28);
    }
}
