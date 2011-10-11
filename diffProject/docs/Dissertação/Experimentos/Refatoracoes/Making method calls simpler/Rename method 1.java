package refactoring;

public class Main {

    public void printFeatures(String name, int age) {
        print();
        details(name, age);
    }

    private void print() {
        System.out.println("Personal Details");
    }

    private void details(String name, int age) {
        //print details
        System.out.println("name" + name);
        System.out.println("age" + age);
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.printFeatures("Fernanda Floriano", 28);
    }
}
