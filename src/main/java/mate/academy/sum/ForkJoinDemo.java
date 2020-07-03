package mate.academy.sum;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private static final int NUM_OF_OPERATIONS = 1_000_000;
    private static final int NUM_OF_THREADS = Runtime.getRuntime()
            .availableProcessors();
    private int from;
    private int to;
    private List<Integer> list;

    public ForkJoinDemo(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Long compute() {
        if ((to - from) <= (NUM_OF_OPERATIONS / NUM_OF_THREADS)) {
            Long sum = 0L;
            for (int i = from; i < to; i++) {
                sum += list.get(i);
            }
            return sum;
        }
        int middle = (to + from) / 2;
        ForkJoinDemo firstHalf = new ForkJoinDemo(from, middle);
        firstHalf.fork();
        ForkJoinDemo secondHalf = new ForkJoinDemo(middle + 1, to);
        long secondValue = secondHalf.compute();
        return firstHalf.join() + secondValue;
    }
}
