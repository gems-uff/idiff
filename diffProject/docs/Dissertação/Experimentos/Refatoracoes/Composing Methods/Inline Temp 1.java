package refactoring;

public class Main {

    public static void main(String[] args) {
        System.out.println("Is Young? " + checkAge());
    }

    private static boolean checkAge() {
        int averageAge = young.averageAge();
        return (averageAge < 21);
    }
}

class young {

    public static int averageAge() {
        return 18;
    }
}