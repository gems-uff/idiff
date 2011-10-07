package refactoring;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.foundPerson(new String[]{"Don", "Carl", "Charles"}));
    }

    private String foundPerson(String[] people) {
        List<String> cadidates = Arrays.asList(new String[]{"Don", "John", "Kent"});
        for (int i = 0; i < people.length; i++) {
            if (cadidates.contains(people[i])) {
                return people[i];
            }
        }
        return "";
    }
}