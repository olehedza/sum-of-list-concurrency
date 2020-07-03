package mate.academy.sum;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final int NUM_OF_OPERATIONS = 1_000_000;

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        List<Integer> integerList = ListNumberGenerator
                .generateListRandom(NUM_OF_OPERATIONS);

        ExecutorServiceSum executorService = new ExecutorServiceSum(integerList);
        Long serviceSum = executorService.getSum();
        executorService.shutdown();
        System.out.println("ExecutorService sum: " + serviceSum);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0, NUM_OF_OPERATIONS);
        forkJoinDemo.setList(integerList);
        Long forkJoinSum = forkJoinPool.invoke(forkJoinDemo);
        System.out.println("ForkJoinPool sum: " + forkJoinSum);
    }
}
