package functional.programming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalTest {
    public static void main(String[] args) {
        var names = new ArrayList<>(List.of("John", "Illia", "Roe", "Joe", "Chandler", "Ross", "Monica", "Rachel", "Phoebe"));

        System.out.println(getOddNames(names));
    }

    public static String getOddNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(n -> (n & 1) == 1)
                .mapToObj(x -> x + ". " + names.get(x))
                .collect(Collectors.joining(", "));
    }

}
