package refactoring;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.foundPerson(new String[]{"Don", "Carl", "Charles"}));
    }

    private String foundPerson(String[] people) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals("Don")) {
                return "Don";
            }
            if (people[i].equals("John")) {
                return "John";
            }
            if (people[i].equals("Kent")) {
                return "Kent";
            }
        }
        return "";
    }
}