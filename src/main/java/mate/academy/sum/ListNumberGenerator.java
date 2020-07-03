package mate.academy.sum;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListNumberGenerator {

    public static List<Integer> generateList(int size) {
        return IntStream.rangeClosed(1, size)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Integer> generateListRandom(long size) {
        return new Random().ints(size, 1, 100)
                .boxed()
                .collect(Collectors.toList());
    }
}
