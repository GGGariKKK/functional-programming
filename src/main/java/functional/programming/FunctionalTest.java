package functional.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FunctionalTest {
    public static void main(String[] args) {
        var names = new ArrayList<>(List.of("John", "Illia", "Roe", "Joe", "Chandler", "Ross", "Monica", "Rachel", "Phoebe"));

        System.out.println("Task 1:");
        System.out.println(getOddNames(names));

        System.out.println("\nTask 2:");
        System.out.println(getUpperSortedDescending(names));

        System.out.println("\nTask 3:");
        System.out.println(concatArray(new String[]{"1, 2, 0", "4, 5"}));

        System.out.println("\nTask 4:");
        Stream<Long> stream = getInfinitePseudoRandomStream(6L).limit(15);
        System.out.println(stream.toList());

        System.out.println("\nTask 5:");
        Stream<Integer> st1 = Stream.iterate(0, x -> ++x); //Infinite integer stream starting from 0, 1, 2, 3, ...
        Stream<Integer> st2 = Stream.of(6, 7, 8, 9, 10);
        Stream<Integer> result = zip(st1, st2);
        System.out.println(result.toList());
    }

    public static String getOddNames(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(n -> (n & 1) == 1)
                .mapToObj(x -> x + ". " + names.get(x))
                .collect(Collectors.joining(", "));
    }

    public static List<String> getUpperSortedDescending(List<String> lines) {
        return lines.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static String concatArray(String[] arr){
        return Arrays.stream(arr)
                .map(element -> element.split(", "))
                .flatMap(Arrays::stream)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> getInfinitePseudoRandomStream(Long seed){
        var a = 25214903917L;
        var c = 11L;
        var m = (long) Math.pow(2, 48);
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
       Stream.Builder<T> result = Stream.builder();

        var fStream = first.iterator();
        var sStream = second.iterator();

        while (fStream.hasNext() && sStream.hasNext()){
            result.accept(fStream.next());
            result.accept(sStream.next());
        }

        return result.build();
    }
}
